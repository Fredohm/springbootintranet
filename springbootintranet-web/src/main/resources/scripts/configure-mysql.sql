# docker run --name fredohm-mysql -p 3306:3306 MYSQL_ROOT_PASSWORD=root -d mysql
#
# Create databases
CREATE DATABASE intranet_dev;
CREATE DATABASE intranet_prod;
#
# Local MySQL server
# Create service accounts
CREATE USER 'intranet_dev_user'@'localhost' IDENTIFIED BY 'root';
CREATE USER 'intranet_prod_user'@'localhost' IDENTIFIED BY 'root';
#
# Grants
GRANT SELECT ON intranet_dev.* to 'intranet_dev_user'@'localhost';
GRANT INSERT ON intranet_dev.* to 'intranet_dev_user'@'localhost';
GRANT UPDATE ON intranet_dev.* to 'intranet_dev_user'@'localhost';
GRANT DELETE ON intranet_dev.* to 'intranet_dev_user'@'localhost';
GRANT SELECT ON intranet_prod.* to 'intranet_prod_user'@'localhost';
GRANT INSERT ON intranet_prod.* to 'intranet_prod_user'@'localhost';
GRANT UPDATE ON intranet_prod.* to 'intranet_prod_user'@'localhost';
GRANT DELETE ON intranet_prod.* to 'intranet_prod_user'@'localhost';
#
# Docker MySQL server
# Create service accounts
CREATE USER 'intranet_dev_user'@'%' IDENTIFIED BY 'root';
CREATE USER 'intranet_prod_user'@'%' IDENTIFIED BY 'root';
#
# Grants
GRANT SELECT ON intranet_dev.* to 'intranet_dev_user'@'%';
GRANT INSERT ON intranet_dev.* to 'intranet_dev_user'@'%';
GRANT UPDATE ON intranet_dev.* to 'intranet_dev_user'@'%';
GRANT DELETE ON intranet_dev.* to 'intranet_dev_user'@'%';
GRANT SELECT ON intranet_prod.* to 'intranet_prod_user'@'%';
GRANT INSERT ON intranet_prod.* to 'intranet_prod_user'@'%';
GRANT UPDATE ON intranet_prod.* to 'intranet_prod_user'@'%';
GRANT DELETE ON intranet_prod.* to 'intranet_prod_user'@'%';