package com.tmg.generator.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.tmg.gererator.domain.Attribute;
import com.tmg.gererator.domain.Embeddable;
import com.tmg.gererator.domain.Table;

public class Test {

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		DataBaseMetaData data = new DataBaseMetaDataImpl();
		Velocity.init();
		VelocityContext context = new VelocityContext();
		Template template = Velocity
				.getTemplate("/src/main/resources/templates/Entity.vm");
		context.put("PackageName", "com.tmg.data");
		List<Table> tables = null;
		try {
			tables = data.getTables("eBenefitSyncMaster", null, null,
					new String[] { "Table" });
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}
		for (Table table : tables) {
			File file = new File("src/generated/com/tmg/data/");
			file.mkdirs();
			if (table.isEmbeddable()) {
				List<Attribute> existingAttributes = table.getAttributes();
				List<Attribute> deleteAttributes = new ArrayList<Attribute>();
				List<Attribute> attributes = new ArrayList<Attribute>();
				Embeddable embeddable = new Embeddable();
				embeddable.setName(table.getTableName() + "EmbeddableId");
				for (Attribute attribute : existingAttributes) {
					if (attribute.isPrimaryKey()) {
						attributes.add(attribute);
						deleteAttributes.add(attribute);
					}
				}
				existingAttributes.removeAll(deleteAttributes);
				embeddable.setAttributes(attributes);
				table.setAttributes(existingAttributes);
				table.setEmbeddableName(table.getTableName() + "EmbeddableId");
				Template temp = Velocity
						.getTemplate("/src/main/resources/templates/Embeddable.vm");
				context.put("PackageName", "com.tmg.data");
				context.put("Embeddable", embeddable);
				FileWriter w;
				try {
					w = new FileWriter("src/generated/com/tmg/data/"
							+ embeddable.getName() + ".java");
					temp.merge(context, w);
					w.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			context.put("Table", table);
			FileWriter w;
			try {
				w = new FileWriter("src/generated/com/tmg/data/"
						+ table.getTableName() + ".java");
				template.merge(context, w);
				w.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Hello");

		System.out.println(System.currentTimeMillis());
	}
}
