cd book-ws
source src/.env
export $(cut -d= -f1 src/.env)

# Setup mysql CEK JANG BENER APA KAGA
if [ $MYSQL_PASSWORD ]
then
  mysql -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "drop database bookws;"
  mysql -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "create database bookws;"
  mysql -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" bookws < db.sql
else
  mysql -u "$MYSQL_USER" -e "drop database bookws;"
  mysql -u "$MYSQL_USER" -e "create database bookws;"
  mysql -u "$MYSQL_USER" bookws < db.sql
fi