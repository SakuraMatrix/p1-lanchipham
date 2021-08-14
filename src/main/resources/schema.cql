CREATE KEYSPACE IF NOT EXISTS budgetReminder WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE budgetReminder;

CREATE TABLE IF NOT EXISTS categories (
        categoryId int,
        name text,
        budgetAmount double,
        currentUse double,
        alertAmount double,
        status text,
        PRIMARY KEY (categoryId, name)
        );