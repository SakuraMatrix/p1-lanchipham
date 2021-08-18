CREATE KEYSPACE IF NOT EXISTS budget WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE budget;

CREATE TABLE IF NOT EXISTS categories (
        categoryId int PRIMARY KEY,
        name text,
        budgetAmount double,       
        alertAmount double,
        currentUse double,
        status text
        );

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (1, 'housing', 1000, 1000, 1000, 'at limit');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (2, 'utilities', 200, 150, 175, 'near limit!');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (3, 'transportation', 250, 200, 75, 'good');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (4, 'food', 300, 280, 290, 'near limit!');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (5, 'medical', 200, 180, 120, 'good');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (6, 'personal care', 50, 40, 75, 'over budget!');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (7, 'entertainment', 100, 90, 120, 'over budget!');

INSERT INTO budget.categories (categoryId, name, budgetAmount, alertAmount, currentUse, status)
  VALUES (8, 'miscellaneous', 150, 120, 150, 'at limit!');