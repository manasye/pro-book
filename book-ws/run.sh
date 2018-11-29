cd book-ws
source src/.env
export $(cut -d= -f1 src/.env)

# Run app
# TODO: Belom tau gimana run javanya
cp out/artifacts/book_ws_war/book-ws_war.war "$CATALINA_HOME/webapps/book-ws_war.war"
catalina run