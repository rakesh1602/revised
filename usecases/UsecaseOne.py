import matplotlib.pyplot as plt  # plotting library for visualization
import numpy as np  # mathematical array
from sklearn import datasets, linear_model  # machine learning library #linear_model module from sklearn contains
# various linear regression models.
from sklearn.metrics import mean_squared_error

disease = datasets.load_diabetes()  # load the diabetes dataset which is inbuilt
# The dataset contains various features related to diabetes, such as age, sex, body mass index (BMI), blood pressure,
# etc.
disease_X = disease.data[:, np.newaxis, 2]
# np.newaxis is used to reshape the feature array to have a shape of (n_samples, 1) for compatibility with the linear
# regression model. only the third feature (column index 2) is selected from the feature data
print(disease)

# Splitting the data
disease_X_train = disease_X[:-30]
disease_X_test = disease_X[-20:]

disease_y_train = disease.target[:-30]
disease_y_test = disease.target[-20:]

reg = linear_model.LinearRegression()
reg.fit(disease_X_train, disease_y_train)
# An instance of the LinearRegression class from sklearn.linear_model is created as reg.
# The fit() function is called on reg to train the model using the training data (disease_X_train and disease_y_train).

y_predict = reg.predict(disease_X_test)
# uses the trained linear regression model (reg) to make predictions on the test feature data (disease_X_test).

accuracy = mean_squared_error(disease_y_test, y_predict) # The mean squared error is a measure of how well the model
# performs.

print(accuracy)

weights = reg.coef_
intercept = reg.intercept_
print(weights, intercept)

plt.scatter(disease_X_test, disease_y_test)
plt.plot(disease_X_test, y_predict)
plt.show()
