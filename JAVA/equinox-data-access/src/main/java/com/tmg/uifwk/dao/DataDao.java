package com.tmg.uifwk.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.tmg.uifwk.codegenerator.BaseData;
import com.tmg.uifwk.codegenerator.ServiceGenerator;

public class DataDao extends BaseDataDao {

	private ServiceGenerator serviceGenerator;

	public Object getData(Object sampleObject, String version, Long instanceId) {
		Thread.currentThread().setContextClassLoader(
				sampleObject.getClass().getClassLoader());
		populateObject(sampleObject, version, instanceId, false);
		return sampleObject;
	}

	private void populateObject(Object sampleObject, String version,
			long objectInstanceId, boolean treatAsParentId) {

		Field[] fields = sampleObject.getClass().getDeclaredFields();
		populateStringDataElements(sampleObject, version, objectInstanceId,
				treatAsParentId);
		populateDateDataElements(sampleObject, version, objectInstanceId,
				treatAsParentId);
		populateNumericDataElements(sampleObject, version, objectInstanceId,
				treatAsParentId);

		for (Field field : fields) {
			Type fieldType = field.getType();

			if (fieldType == String.class || fieldType == Long.class
					|| fieldType == Boolean.class || fieldType == Double.class
					|| fieldType == Date.class) {
				// Just loop through these fields because we've taken care of
				// these in the previous populate** calls
				continue;
			}

			if (fieldType == List.class) {
				Type genericType = field.getGenericType();
				if (genericType instanceof ParameterizedType) {
					ParameterizedType ptype = (ParameterizedType) genericType;
					genericType = ptype.getActualTypeArguments()[0];
				}
				String actualClassName = genericType.toString();
				actualClassName = actualClassName.replace("class ", "");

				BaseData sampleObjectBase = (BaseData) sampleObject;

				HashMap<Long, Object> innerObjects = getUniqueInstancesForData(
						sampleObjectBase.getInstanceID(), actualClassName);

				for (Long uid : innerObjects.keySet()) {
					Object obj = innerObjects.get(uid);

					BaseData baseData = (BaseData) obj;
					baseData.setInstanceID(uid);

					populateObject(obj, version, uid, false);
				}

				try {
					Class<?> clazz = serviceGenerator
							.getClassOf(actualClassName);
					List innerList = createListOfType(clazz);
					innerList.addAll(innerObjects.values());
					Method m = sampleObject.getClass().getDeclaredMethod(
							"set" + field.getName(), List.class);

					m.invoke(sampleObject, innerList);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			String canonicalName = field.getType().getCanonicalName();
			if (canonicalName.startsWith("com.tmg.equinox.services")) {
				Object childObject = serviceGenerator
						.getInstance(canonicalName);
				populateObject(childObject, version, objectInstanceId, true);

				BaseData childObjectBase = (BaseData) childObject;

				try {
					if (childObjectBase.getInstanceID() != null) {
						String fieldName = field.getName();

						Method m = sampleObject.getClass().getDeclaredMethod(
								"set" + fieldName,
								new Class[] { childObject.getClass() });
						m.invoke(sampleObject, childObject);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}

	private HashMap<Long, Object> getUniqueInstancesForData(
			long objectInstanceId, String actualClassName) {
		HashMap<Long, Object> innerObjects = new HashMap<Long, Object>();
		List<Long> uniqueInstances = uniqueInstancesForData(objectInstanceId,
				"StringDataValue");
		for (Long uid : uniqueInstances) {
			Object innerObject = serviceGenerator.getInstance(actualClassName);
			innerObjects.put(uid, innerObject);
			createListOfType(innerObject.getClass());
		}

		uniqueInstances = uniqueInstancesForData(objectInstanceId,
				"DateDataValue");

		for (Long uid : uniqueInstances) {
			Object innerObject = innerObjects.get(uid);
			if (innerObject == null) {
				innerObject = serviceGenerator.getInstance(actualClassName);
				innerObjects.put(uid, innerObject);
			}
		}

		uniqueInstances = uniqueInstancesForData(objectInstanceId,
				"NumericDataValue");

		for (Long uid : uniqueInstances) {
			Object innerObject = innerObjects.get(uid);
			if (innerObject == null) {
				innerObject = serviceGenerator.getInstance(actualClassName);
				innerObjects.put(uid, innerObject);
			}
		}
		return innerObjects;
	}

	private static <T> List<T> createListOfType(Class<T> type) {
		return new ArrayList<T>();
	}

	private List<Long> uniqueInstancesForData(long id, String table) {
		List<Long> result = new ArrayList<Long>();
		DataSource ds = context.getBean("uifwkDS", DataSource.class);

		try {
			Connection conn = ds.getConnection();

			String query = "SELECT DISTINCT ObjInstanceID from DM.ObjectDefinition obj "
					+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
					+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
					+ "left outer join Data."
					+ table
					+ " sval on sval.ObjVerID = map.ObjVerID "
					+ "where sval.ParentObjInstanceID = ? ";

			PreparedStatement pstmt = conn.prepareStatement(query,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				long objInstanceId = rs.getLong("ObjInstanceID");
				result.add(objInstanceId);
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void populateStringDataElements(Object sampleObject, String version,
			Long id, boolean treatAsParentId) {

		DataSource ds = context.getBean("uifwkDS", DataSource.class);

		try {
			Connection conn = ds.getConnection();

			String query = "select obj.*, map.ObjVerID,attr.*,sval.* from DM.ObjectDefinition obj "
					+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
					+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
					+ "left outer join Data.StringDataValue sval on sval.ObjVerID = map.ObjVerID "
					+ "where sval.ObjInstanceID = ? ORDER BY obj.ObjectName";

			if (treatAsParentId) {
				query = "select obj.*, map.ObjVerID,attr.*,sval.* from DM.ObjectDefinition obj "
						+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
						+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
						+ "left outer join Data.StringDataValue sval on sval.ObjVerID = map.ObjVerID "
						+ "where sval.ParentObjInstanceID = ? ORDER BY obj.ObjectName";

			}

			PreparedStatement pstmt = conn.prepareStatement(query,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String value = rs.getString("Value");
				if (value == null) {
					continue;
				}

				String fieldName = rs.getString("Name");

				try {
					if (BeanUtils.getProperty(sampleObject, "instanceID") == null) {

						Long instanceId = rs.getLong("ObjInstanceId");
						BeanUtils.setProperty(sampleObject, "instanceID",
								instanceId);
					}
				} catch (Exception e) {
					// Will not happen as a Bean always has a instanceID
					// property.
				}
				try {
					Field field = sampleObject.getClass().getDeclaredField(fieldName);
					if(field.getType() == Boolean.class) {
						Method setter = sampleObject.getClass().getDeclaredMethod(
								"set" + fieldName, Boolean.class);
						if (setter != null) {
							setter.invoke(sampleObject, new Boolean(value));
						}
						continue;
					}
				} catch (Exception e1) {					
					e1.printStackTrace();
				} 
				try {
					Method setter = sampleObject.getClass().getDeclaredMethod(
							"set" + fieldName, String.class);
					if (setter != null) {
						setter.invoke(sampleObject, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void populateDateDataElements(Object sampleObject, String version,
			Long id, boolean treatAsParentId) {

		DataSource ds = context.getBean("uifwkDS", DataSource.class);

		try {
			Connection conn = ds.getConnection();

			String query = "select obj.*, map.ObjVerID,attr.*,dval.* from DM.ObjectDefinition obj "
					+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
					+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
					+ "left outer join Data.DateDataValue dval on dval.ObjVerID = map.ObjVerID "
					+ "where dval.ObjInstanceID = ? ORDER BY obj.ObjectName";

			if (treatAsParentId) {
				query = "select obj.*, map.ObjVerID,attr.*,dval.* from DM.ObjectDefinition obj "
						+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
						+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
						+ "left outer join Data.DateDataValue dval on dval.ObjVerID = map.ObjVerID "
						+ "where dval.ParentObjInstanceID = ? ORDER BY obj.ObjectName";

			}

			PreparedStatement pstmt = conn.prepareStatement(query,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String value = rs.getString("Value");
				if (value == null) {
					continue;
				}

				String field = rs.getString("Name");

				try {
					if (BeanUtils.getProperty(sampleObject, "instanceID") == null) {

						Long instanceId = rs.getLong("ObjInstanceId");
						BeanUtils.setProperty(sampleObject, "instanceID",
								instanceId);
					}
				} catch (Exception e) {
					// Will not happen as a Bean always has a instanceID
					// property.
				}

				try {
					Method setter = sampleObject.getClass().getDeclaredMethod(
							"set" + field, String.class);
					if (setter != null) {
						setter.invoke(sampleObject, value);
					}
				} catch (Exception e) {
					// It means the current object does not have the field
					// declared
					e.printStackTrace();
				}
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void populateNumericDataElements(Object sampleObject,
			String version, Long id, boolean treatAsParentId) {

		DataSource ds = context.getBean("uifwkDS", DataSource.class);

		try {
			Connection conn = ds.getConnection();

			String query = "select obj.*, map.ObjVerID,attr.*,nval.* from DM.ObjectDefinition obj "
					+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
					+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
					+ "left outer join Data.NumericDataValue nval on nval.ObjVerID = map.ObjVerID "
					+ "where nval.ObjInstanceID = ? ORDER BY obj.ObjectName";

			if (treatAsParentId) {
				query = "select obj.*, map.ObjVerID,attr.*,nval.* from DM.ObjectDefinition obj "
						+ "left outer join DM.ObjectVersionAttribXref map on obj.OID = map.OID "
						+ "left outer join DM.Attribute attr on map.AttrID = attr.AttrID "
						+ "left outer join Data.NumericDataValue nval on nval.ObjVerID = map.ObjVerID "
						+ "where nval.ParentObjInstanceID = ? ORDER BY obj.ObjectName";

			}

			PreparedStatement pstmt = conn.prepareStatement(query,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String value = rs.getString("Value");
				if (value == null) {
					continue;
				}

				String field = rs.getString("Name");

				try {
					if (BeanUtils.getProperty(sampleObject, "instanceID") == null) {

						Long instanceId = rs.getLong("ObjInstanceId");
						BeanUtils.setProperty(sampleObject, "instanceID",
								instanceId);
					}
				} catch (Exception e) {
					// Will not happen as a Bean always has a instanceID
					// property.
				}

				try {
					Method setter = sampleObject.getClass().getDeclaredMethod(
							"set" + field, String.class);
					if (setter != null) {
						setter.invoke(sampleObject, value);
					}
				} catch (Exception e) {
					// It means the current object does not have the field
					// declared
					e.printStackTrace();
				}
			}

			rs.close();
			pstmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ServiceGenerator getServiceGenerator() {
		return serviceGenerator;
	}

	public void setServiceGenerator(ServiceGenerator serviceGenerator) {
		this.serviceGenerator = serviceGenerator;
	}

}
