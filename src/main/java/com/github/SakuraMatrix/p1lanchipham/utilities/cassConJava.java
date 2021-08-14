package com.github.SakuraMatrix.p1lanchipham.utilities;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;

public class cassConJava {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {
            CreateKeyspace myKeySpace = SchemaBuilder.createKeyspace("budget").ifNotExists()
                    .withSimpleStrategy(1);
            session.execute(myKeySpace.build());
        }
    }
}
