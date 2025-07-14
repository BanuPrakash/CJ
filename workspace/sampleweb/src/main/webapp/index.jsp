<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <h1>Product Application</h1>
  <div>
      Welcome, ${sessionScope.user} &nbsp;&nbsp;&nbsp;
      <a href="logout.do">Logout</a>
  </div>
  <div>
      ${param.msg}
  </div>
 <a href="/sample.do">Sample Servlet</a> <br />
 <a href="/products.do">Get all products</a> <br />
 <a href="productForm.html">Add Product</a>
</body>
</html>