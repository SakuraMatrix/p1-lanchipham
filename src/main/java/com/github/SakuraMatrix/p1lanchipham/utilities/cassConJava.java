package com.github.SakuraMatrix.p1lanchipham.utilities;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;

//method to connect java project to Cassandra DB

public class cassConJava {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {
            CreateKeyspace budget = SchemaBuilder.createKeyspace("budget").ifNotExists()
                    .withSimpleStrategy(1);
            session.execute(budget.build());
        }
    }
}
