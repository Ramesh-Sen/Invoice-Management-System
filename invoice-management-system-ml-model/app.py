import json
from flask import Flask, request

app = Flask(__name__)


@app.route('/', methods=['Get'])
def hello():
    print('Hello')
    return 'Hello, World!'


@app.route('/predict', methods=['POST'])
def predict():
    print('Predict')
    data = request.args.get("data")
    data1 = request.args.get("data")
    print("JSON LOADED----------------------------------", request.args)
    data = json.loads(data)
    app.logger.info(data)
    print("DATA---------------------------------->", data)
    return 'Predict'


if __name__ == '__main__':
    app.run(threaded=True, debug=True)
