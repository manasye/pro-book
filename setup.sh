chmod +x ./bank-ws/setup.sh
chmod +x ./book-ws/setup.sh
chmod +x ./pro-book/setup.sh

tmux split-window ./bank-ws/setup.sh
tmux split-window ./book-ws/setup.sh
tmux split-window ./pro-book/setup.sh
