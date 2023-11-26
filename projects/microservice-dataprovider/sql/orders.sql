-- 创建订单表
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    OrderDate DATE,
    OrderName VARCHAR(255) -- 添加订单名称字段
);

-- 插入示例数据
INSERT INTO Orders (OrderID, CustomerID, ProductID, Quantity, Price, OrderDate, OrderName)
VALUES
    (1, 101, 201, 2, 50.00, '2023-01-01', 'Order 1'),
    (2, 102, 202, 1, 30.00, '2023-01-02', 'Order 2'),
    (3, 103, 201, 3, 75.00, '2023-01-03', 'Order 3'),
    (4, 101, 203, 1, 20.00, '2023-01-04', 'Order 4'),
    (5, 104, 204, 2, 60.00, '2023-01-05', 'Order 5');
