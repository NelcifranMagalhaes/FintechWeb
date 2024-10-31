

DROP TABLE balance;
DROP TABLE investment;
DROP TABLE money_in;
DROP TABLE money_out;
DROP table fintech_user;
DROP SEQUENCE seq_fintech_user;
DROP SEQUENCE seq_money_in;
DROP SEQUENCE seq_money_out;
DROP SEQUENCE seq_balance;
DROP SEQUENCE seq_investment;


-- FintechUser
CREATE TABLE fintech_user (
                              id NUMBER(10) PRIMARY KEY,
                              name VARCHAR2(100)   NOT NULL,
                              email VARCHAR2(50)   NOT NULL,
                              gender CHAR(1)        NOT NULL,
                              birth_date DATE NOT NULL,
                              password_hash VARCHAR2(255) NOT NULL,
                              created_at DATE NOT NULL
);
CREATE SEQUENCE seq_fintech_user
    START WITH 1
    INCREMENT BY 1;
-- SELECT * FROM fintech_user;

-- MoneyIn
CREATE TABLE money_in (
                          id NUMBER(10) PRIMARY KEY,
                          label VARCHAR2(100)   NOT NULL,
                          value FLOAT(100) NOT NULL,
                          created_at DATE NOT NULL,
                          fintech_user_id NUMBER(10),
                          CONSTRAINT fk_money_in_fintech_user FOREIGN KEY (fintech_user_id)
                              REFERENCES fintech_user(id));

CREATE SEQUENCE seq_money_in
    START WITH 1
    INCREMENT BY 1;

-- MoneyOut

CREATE TABLE money_out (
                           id NUMBER(10) PRIMARY KEY,
                           label VARCHAR2(100)   NOT NULL,
                           value FLOAT NOT NULL,
                           category VARCHAR2(100) NOT NULL,
                           created_at DATE NOT NULL,
                           fintech_user_id NUMBER(10),
                           CONSTRAINT fk_money_out_fintech_user FOREIGN KEY (fintech_user_id)
                               REFERENCES fintech_user(id));

CREATE SEQUENCE seq_money_out
    START WITH 1
    INCREMENT BY 1;

--Balance;
CREATE TABLE balance (
                         id NUMBER(10) PRIMARY KEY,
                         value FLOAT NOT NULL,
                         created_at DATE NOT NULL,
                         fintech_user_id NUMBER(10),
                         CONSTRAINT fk_balance_fintech_user FOREIGN KEY (fintech_user_id)
                             REFERENCES fintech_user(id));
CREATE SEQUENCE seq_balance
    START WITH 1
    INCREMENT BY 1;

-- Investment;
CREATE TABLE investment (
                            id NUMBER(10) PRIMARY KEY,
                            value FLOAT NOT NULL,
                            label VARCHAR2(100)   NOT NULL,
                            created_at DATE NOT NULL,
                            fintech_user_id NUMBER(10),
                            CONSTRAINT fk_investment_fintech_user FOREIGN KEY (fintech_user_id)
                                REFERENCES fintech_user(id));
CREATE SEQUENCE seq_investment
    START WITH 1
    INCREMENT BY 1;



