chmod +x ./bank-ws/run.sh
chmod +x ./book-ws/run.sh
chmod +x ./pro-book/run.sh

tmux split-window ./bank-ws/run.sh
tmux split-window ./book-ws/run.sh
tmux split-window ./pro-book/run.sh