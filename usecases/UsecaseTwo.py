import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.impute import SimpleImputer

cars = pd.read_csv("cars.csv")
print(cars.head(10))
print(cars.columns)

# Drop rows with missing values
cars.dropna(subset=['Horsepower', 'Price_in_thousands'], inplace=True)
#This line drops the rows that contain missing values in the 'Horsepower' and 'Price_in_thousands' columns. The dropna() method with the subset parameter is used to specify the columns where missing values are checked. The inplace=True argument ensures that the changes are made directly to the cars dataframe.

x = cars['Horsepower'].values.reshape(-1, 1)
y = cars['Price_in_thousands'].values.reshape(-1, 1)

#Here, we extract the 'Horsepower' and 'Price_in_thousands' columns from the cars dataframe and reshape them into 2D arrays using .values.reshape(-1, 1). Reshaping is done to ensure that the data is in the appropriate format for scikit-learn's LinearRegression model.

# Impute missing values using mean imputation
imputer = SimpleImputer(strategy='mean')
x = imputer.fit_transform(x)
y = imputer.fit_transform(y)

reg = LinearRegression()
reg.fit(x, y)

print(reg.coef_[0][0])
print(reg.intercept_[0])

predictions = reg.predict(x)

plt.figure(figsize=(16, 8))
plt.scatter(
    cars['Horsepower'],
    cars['Price_in_thousands'],
    c='black'
)

plt.plot(
    cars['Horsepower'],
    predictions,
    c='blue'
)
plt.xlabel('Horsepower')
plt.ylabel('Price')
plt.show()
