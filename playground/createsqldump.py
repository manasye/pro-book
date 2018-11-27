import requests
import json
import random

# helper
def parse_id(data):
	try:
		return data['id']
	except:
		print(data.keys())

def parse_price(data):
	try:
		return int(data['saleInfo']['listPrice']['amount'])
	except:
		return random.randrange(30, 150, 10) * 1000

def parse_category(data):
	try:
		return data['volumeInfo']['categories'][0]
	except:
		return 'Others'

def generate_random_sold(data):
	return random.randrange(10, 1000, 10)

# book:
# - id
# - price
def parse_book(data):
	book_id = parse_id(data)
	book_price = parse_price(data)
	return 'INSERT INTO book VALUES ("{}", {});'.format(book_id, book_price)

# sold
# - id
# - category
# - count
def parse_sold(data):
	sold_id = parse_id(data)
	sold_category = parse_category(data)
	sold_count = generate_random_sold(data)
	return 'INSERT INTO sold VALUES ("{}", "{}", {});'.format(sold_id, sold_category, sold_count)

search_terms = ['react', 'harry+potter', 'software+engineering', 'lele']

books = []
solds = []
for search_term in search_terms:
	res = requests.get('https://www.googleapis.com/books/v1/volumes?q={}'.format(search_term))
	if res.status_code != 200:
		print('[FAIL] fail to get books for "{}"'.format(search_term))
		continue

	data = json.loads(res.content)
	for book_data in data['items']:
		books.append(parse_book(book_data))
		if random.randint(0, 10) >= 2:
			solds.append(parse_sold(book_data))

# Write to sql file
file = open("db.sql", "w")

for book in books:
	file.write(book + '\n')

file.write('\n\n')

for sold in solds:
	file.write(sold + '\n')

file.close()

