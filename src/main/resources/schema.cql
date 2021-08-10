CREATE KEYSPACE IF NOT EXISTS budgetReminder WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

        USE budgetReminder;

        CREATE TABLE IF NOT EXISTS category (
        category_id int,
        name text,
        budgetAmount double,
        limit double,
        PRIMARY KEY (category_id, name)
        );