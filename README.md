# Book-Management APIs

## Table of Contents

- [Add New Book](#add-new-book)
- [Get Books](#get-books)
- [Update Book](#update-book)
- [Delete Book](#delete-book)

---
## Add New Book

#### HTTP Method: **POST**

#### URL:  /bookManagement/books
#### Content-Type: application/json
#### Request Parameters:

|Parameter Name|Data Type|Required?|Description|
|:--|:--|:--:|:--|
|isbn|string|Y|書籍編號|
|name|string|Y|書名|
|author|string|Y|作者|
|publisher|string|Y|出版社|
|publicationDate|string|Y|出版日期，格式: yyy-MM-dd|
|price|string|Y|定價|
|translator|string|N|譯者|

```json
{
	"isbn":"9789862017050",
	"name":"無瑕的程式碼：敏捷軟體開發技巧守則",
	"author":"Robert C. Martin",
	"publisher":"博碩",
	"publicationDate":"2013-03-22",
	"price":"580"
}
```


#### Response

|Parameter Name|Data Type|Description|
|:--|:--|:--|
|resultStatus.success|boolean|成功或失敗|
|resultStatus.errorCode|string|reason code|
|resultStatus.errorMsg|string|錯誤訊息|

```json
{
    "resultStatus": {
        "errorCode": null,
        "errorMsg": null,
        "success": true
    }
}
```

#### Response Code:
|Code|Message|Description|
|--:|:--|:--|
|1000001|輸入參數錯誤||
|1000002|ISBN 已存在||
|1000003|系統繁忙中，請稍後在試|未知系統錯誤|

---
## Get Books

#### HTTP Method: **GET**

#### URL:  /bookManagement/books/{page}/{count}
#### Query String:

|Parameter Name|Data Type|Required?|Description|
|:--|:--|:--:|:--|
|author|string|N|作者|
|name|string|N|書名，可模糊查詢|
|isbn|string|N|書籍編號|
|publisher|string|N|出版社|


#### Response

|Parameter Name|Data Type|Description|
|:--|:--|:--|
|resultStatus.success|boolean|成功或失敗|
|resultStatus.errorCode|string|reason code|
|resultStatus.errorMsg|string|錯誤訊息|
|books|array|查詢結果清單|
|books.isbn|string|書籍編號|
|books.name|string|書名|
|books.author|string|作者|
|books.publisher|string|出版社|
|books.publicationDate|string|出版日期，格式: yyy-MM-dd|
|books.price|string|定價|
|books.translator|string|譯者|

```json
{
    "resultStatus": {
        "errorCode": null,
        "errorMsg": null,
        "success": true
    },
    "books": [
        {
            "name": "無瑕的程式碼：敏捷軟體開發技巧守則",
            "author": "Robert C. Martin",
            "translator": null,
            "publisher": "博碩",
            "publicationDate": "2013-03-22",
            "price": 580.00,
            "isbn": "9789862017050"
        }
    ]
}
```

#### Response Code:
|Code|Message|Description|
|--:|:--|:--|
|1003001|輸入參數錯誤||
|1003002|系統繁忙中，請稍後在試|未知系統錯誤|

---
## Update Book

#### HTTP Method: **PATCH**

#### URL:  /bookManagement/books
#### Content-Type: application/json
#### Request Parameters:

|Parameter Name|Data Type|Required?|Description|
|:--|:--|:--:|:--|
|isbn|string|Y|書籍編號|
|name|string|N|書名|
|author|string|N|作者|
|publisher|string|N|出版社|
|publicationDate|string|N|出版日期，格式: yyy-MM-dd|
|price|string|N|定價|
|translator|string|N|譯者|

```json
{
	"isbn":"9789862017050",
	"translator":"戴于晉、博碩文化"
}
```


#### Response

|Parameter Name|Data Type|Description|
|:--|:--|:--|
|resultStatus.success|boolean|成功或失敗|
|resultStatus.errorCode|string|reason code|
|resultStatus.errorMsg|string|錯誤訊息|

```json
{
    "resultStatus": {
        "errorCode": null,
        "errorMsg": null,
        "success": true
    }
}
```

#### Response Code:
|Code|Message|Description|
|--:|:--|:--|
|1002001|輸入參數錯誤||
|1002002|查無 ISBN||
|1002003|系統繁忙中，請稍後在試|未知系統錯誤|

---
## Delete Book

#### HTTP Method: **DELETE**

#### URL:  /bookManagement/books/{isbn}
#### Query String:

|Parameter Name|Data Type|Required?|Description|
|:--|:--|:--:|:--|


#### Response

|Parameter Name|Data Type|Description|
|:--|:--|:--|
|resultStatus.success|boolean|成功或失敗|
|resultStatus.errorCode|string|reason code|
|resultStatus.errorMsg|string|錯誤訊息|

```json
{
    "resultStatus": {
        "errorCode": null,
        "errorMsg": null,
        "success": true
    }
}
```

#### Response Code:
|Code|Message|Description|
|--:|:--|:--|
|1001001|輸入參數錯誤||
|1001002|刪除失敗，查無 ISBN||
