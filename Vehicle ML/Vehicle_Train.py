import pandas as pd
import pickle

dataset = pd.read_csv('Vehicle_Dataset.csv')

X = dataset.iloc[:,:-1].values

y = dataset.iloc[:,15].values

#Importing Logistic Regression to fit our model
from sklearn.linear_model  import LinearRegression
classifier = LinearRegression()
classifier.fit(X , y)

file_name='Bike_Model.sav'
pickle.dump(classifier, open(file_name, 'wb'))

