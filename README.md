

```bash
sudo -u postgres -i
psql
CREATE DATABASE blockchain;
\c blockchain;
CREATE TABLE blockchain(a NUMERIC, b NUMERIC, c NUMERIC);
```

To compile:
	javac BlockchainTest.java
	java -cp '.:postgresql-42.2.25.jar' BlockchainTest
