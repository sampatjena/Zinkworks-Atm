drop table if exists account;
drop table if exists atmcurrencyinfo;
CREATE TABLE IF NOT EXISTS account ( account_number integer not null primary key, account_status char(1) not null , account_pin char(4) not null , opening_balance_amount integer not null , overdraft_amount integer not null );
--create table account_balance (account_number integer not null primary key, balance_date date not null , opening_balance_amount integer not null , overdraft_amount integer not null , overdraft_date date not null);
CREATE TABLE IF NOT EXISTS atmcurrencyinfo (atm_id integer not null , fifty_euro_count integer not null , twenty_euro_count integer not null, ten_euro_count integer not null, five_euro_count integer not null);