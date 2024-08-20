# importing header files

import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split


# Loading CSV file to dataframe df

df = pd.read_csv(
    "C:\\MY STUFF\\Projects\\Invoice Management System\\1806597.csv")
print(df)

# Droping area_business because it contains all NULL values
df.drop(['area_business'], axis=1, inplace=True)

# Storing rows where clear_date is NULL into another df
df_NaN = df.loc[pd.isnull(df.clear_date)]
df_NaN.reset_index()

# droping rows where clear_date is NULL
df.dropna(subset=['clear_date'], axis=0, inplace=True)

# Droping invoice_id & doc_id because it has unique values
df.drop(['invoice_id'], axis=1, inplace=True)
df.drop(['doc_id'], axis=1, inplace=True)

# droping columns which are constant
df.drop(columns=df.columns[df.nunique() == 1], inplace=True)

# converting column data to date time format
df['clear_date'] = pd.to_datetime(df['clear_date'])
df['posting_date'] = pd.to_datetime(df['posting_date'])
df['document_create_date'] = pd.to_datetime(
    df['document_create_date'], format="%Y%m%d")
df['document_create_date.1'] = pd.to_datetime(
    df['document_create_date.1'], format="%Y%m%d")
df['due_in_date'] = pd.to_datetime(df['due_in_date'], format="%Y%m%d")
df['baseline_create_date'] = pd.to_datetime(
    df['baseline_create_date'], format="%Y%m%d")

# checking for duplicate rows
df.drop_duplicates(keep='first', inplace=True)

# checking faulty datas
df[(df['document_create_date'] <= df['due_in_date'])]

# converting CAD to USD
df['total_open_amount'].where(
    df['invoice_currency'] == 'CAD', 0.74*df['total_open_amount'], inplace=True)

# dropping unnecessary columns
df.drop(['document_create_date.1', 'invoice_currency'], axis=1, inplace=True)

# creating target column delay = clear date - due in date
df['delay'] = df['clear_date'] - df['due_in_date']

# Dropping object type columns
X_train.drop(['posting_date', 'document_create_date', 'cust_number', 'clear_date',
             'due_in_date', 'baseline_create_date', 'cust_payment_terms'], axis=1, inplace=True)
