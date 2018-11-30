cd book-ws
source src/.env
export $(cut -d= -f1 src/.env)

# Run app
# TODO: Belom tau gimana run javanya
rm -rf "$CATALINA_HOME/webapps/ROOT"
rm -rf "$CATALINA_HOME/work"
cp out/artifacts/book_ws_war/book-ws_war.war "$CATALINA_HOME/webapps/ROOT.war"
catalina run