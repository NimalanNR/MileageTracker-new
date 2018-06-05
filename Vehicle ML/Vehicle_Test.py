#Importing time package to make 1 second delay to pump the data
import time
#Importing pandas to read csv file
#Importing pickle to import built model
import pickle
import json
import csv
import pandas as pd

json_filename = 'Bike.json'
fieldnames = ("bike","avg_speed","total_km_travelled","no_signals	","sig_fuel","serviced","service_fuel",
              "shift_to_high_gear","gear_fuel","clean","clean_fuel", "t_wst_fuel", 
              "useful_fuel","mileage","except_sig")

def store_csv_as_json():

    csvfile = open('Vehicle_Test.csv', 'r')
    jsonfile = open(json_filename, 'w')
    reader = csv.DictReader( csvfile, fieldnames)
    for row in reader:
        json.dump(row, jsonfile)
        jsonfile.write('\n')
#Fetching JSON data, Line by line
def get_json_line(line_no):
    count = 0
    with(open(json_filename)) as f:
        for line in f:
            if count == line_no:
                return line
            count += 1
#Converting JSON to Dataframe
def get_row_data(json_data):
    data = json.loads(json_data)
    result = pd.DataFrame([data], columns=fieldnames)
    return result
 
    
def Predict_Y(i):
    file_name='Bike_Model.sav'
    file_object=open(file_name,'rb')
    #Loading pickle file to predict the output
    reg=pickle.load(file_object)
    json_data = get_json_line(i)
    x = get_row_data(json_data)
    #Predicting the Y value using built model - regressor
    y = reg.predict(x)
    print(y)
    time.sleep(1)

if __name__ == "__main__":
    store_csv_as_json()
    #Iterating through all 20 rows in AHU_Test_Dataset.csv
    for i in range(0, 5):
        Predict_Y(i)
    
