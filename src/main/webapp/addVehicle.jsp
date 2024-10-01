<!DOCTYPE html>
<html>
<head>
    <title>Add Vehicle</title>
</head>
<body>
    <h1>Add New Vehicle</h1>
    <form action="manageVehicles" method="post">
        <input type="hidden" name="action" value="add">
        <label>Make:</label>
        <input type="text" name="make"><br>
        <label>Model:</label>
        <input type="text" name="model"><br>
        <label>Price:</label>
        <input type="text" name="price"><br>
        <label>Availability:</label>
        <input type="checkbox" name="availability"><br>
        <input type="submit" value="Add Vehicle">
    </form>
    <a href="manageVehicles">Back to Vehicle List</a>
</body>
</html>
