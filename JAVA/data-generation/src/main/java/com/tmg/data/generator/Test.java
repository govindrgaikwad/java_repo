package com.tmg.data.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class Test {

	public static void main(String[] args) {
		DataBaseMetaData data = new DataBaseMetaDataImpl();
		Velocity.init();
		VelocityContext context = new VelocityContext();
		Template template = Velocity
				.getTemplate("/src/main/resources/templates/Entity.vm");
		context.put("PackageName", "com.tmg.data");
		List<Table> tables = null;
		try {
			tables = data.getTables("UIFramework", null, null,
					new String[] { "Table" });
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Table table : tables) {
			context.put("Table", table);
			FileWriter w;
			try {
				w = new FileWriter("src/main/java/com/tmg/data/" + table.getTableName()
						+ ".java");
				template.merge(context, w);
				w.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Hello");
	}
}
