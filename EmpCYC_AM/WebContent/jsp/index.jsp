<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="userApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>AngularJS User</title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootswatch/3.2.0/sandstone/bootstrap.min.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>  -->
		<script type="text/javascript" src="js/angular.js"></script>
		<script type="text/javascript" src="js/angular-animate.js"></script>
		<script type="text/javascript" src="js/app.js"></script>
		<script type="text/javascript" src="js/controller/userController.js"></script>
	</head>
	<body>
		<div class="container" ng-controller="userController">
			<div ng-show="toggle">
			  <div class="alert alert-info">
				<p>Sort Type: {{ sortType }}</p>
				<p>Sort Reverse: {{ sortReverse }}</p>
				<p>Search Query: {{ searchUser }}</p>
			  </div>
			  
			  <form>
				<div class="form-group">
				  <div class="input-group">
					<div class="input-group-addon"><i class="fa fa-search"></i></div>
					<input type="text" class="form-control" placeholder="Search da user" ng-model="searchUser">
				  </div>      
				</div>
			  </form>
			  
			  <table class="table table-bordered table-hover">
				<thead>
				  <tr>
					<td>
					  <a href="#" ng-click="sortType = 'id'; sortReverse = !sortReverse">
						ID
						<span ng-show="sortType == 'id' && !sortReverse" class="fa fa-caret-down"></span>
						<span ng-show="sortType == 'id' && sortReverse" class="fa fa-caret-up"></span>
					  </a>
					</td>
					<td>
					  <a href="#" ng-click="sortType = 'firstName'; sortReverse = !sortReverse">
					  First Name 
						<span ng-show="sortType == 'firstName' && !sortReverse" class="fa fa-caret-down"></span>
						<span ng-show="sortType == 'firstName' && sortReverse" class="fa fa-caret-up"></span>
					  </a>
					</td>
					<td>
					  <a href="#" ng-click="sortType = 'lastName'; sortReverse = !sortReverse">
					  Last Name 
						<span ng-show="sortType == 'lastName' && !sortReverse" class="fa fa-caret-down"></span>
						<span ng-show="sortType == 'lastName' && sortReverse" class="fa fa-caret-up"></span>
					  </a>
					</td>
					<td>
					  <a href="#" ng-click="sortType = 'email'; sortReverse = !sortReverse">
					  Email 
						<span ng-show="sortType == 'email' && !sortReverse" class="fa fa-caret-down"></span>
						<span ng-show="sortType == 'email' && sortReverse" class="fa fa-caret-up"></span>
					  </a>
					</td><td>
					  <a href="#">Mobile</a>
					</td>
					<td>
					  <a href="#" ng-click="sortType = 'creation_time'; sortReverse = !sortReverse">
					  Created on 
						<span ng-show="sortType == 'creation_time' && !sortReverse" class="fa fa-caret-down"></span>
						<span ng-show="sortType == 'creation_time' && sortReverse" class="fa fa-caret-up"></span>
					  </a>
					</td>
					<td>
					  <a href="#" ng-click="sortType = 'last_update_time'; sortReverse = !sortReverse">
					  Last Updated
						<span ng-show="sortType == 'last_update_time' && !sortReverse" class="fa fa-caret-down"></span>
						<span ng-show="sortType == 'last_update_time' && sortReverse" class="fa fa-caret-up"></span>
					  </a>
					</td>
					<td><a href="#"> Active Status</a></td>
					<td>
					  <a href="#">Modify / Archieve </a>
					</td>
				  </tr>
				</thead>
				<tbody>
				  <tr ng-repeat="user in users | orderBy:sortType:sortReverse | filter:searchUser" ng-include="getTemplate(user)">
				  <script type="text/ng-template" id="display">
						<td>1</td>
				    	<td>{{ user.firstName}}</td>			  
				    	<td>{{ user.lastName}}</td>
				    	<td>{{ user.email}}</td>
				    	<td>{{ user.mobile}}</td>
				    	<td>{{ user.creation_time | date:'medium' }}</td>
				    	<td>{{ user.last_update_time | date:'medium' }}</td>
				    	<td>{{ user.status }}</td>
				    	<td>
				    		&nbsp;<button class="btn btn-info" ng-click="editUser(user)"><i class="fa fa-pencil"></i></button>&nbsp;&nbsp;
				    		<button class="btn" ng-Click='archiveUser(user.email)'><i class="fa fa-archive"></i></button> 
				    	</td>
					</script>
   					<script type="text/ng-template" id="edit">
						<td>1</td>
						<td><input type="text" ng-model=user.firstName class="form-control input-sm"/></td>
						<td><input type="text" ng-model=user.lastName class="form-control input-sm"/></td>
						<td><input type="text" ng-model=user.email class="form-control input-sm"/></td>
						<td><input type="text" ng-model=user.mobile class="form-control input-sm"/></td>
 						<td>{{ user.creation_time | date:'medium' }}</td>
				    	<td>{{ user.last_update_time | date:'medium' }}</td>
				    	<td>{{ user.status }}</td>
						<td>
				   	 		<button type="button" class="btn btn-info btn-sm" ng-click="updateUser(user)">Save</button>
     						<button type="button" class="btn btn-danger btn-sm" ng-click="reset()">Back</button> 
				   		</td>
					</script>
				  </tr>
				</tbody>
			  </table>
			  <button ng-click="toggle = !toggle" class="btn-panel"><i class="panel-title-icon fa fa-plus"></i> Add New User</button>
		  </div>
		  <div id="add-task-panel" class="fadein fadeout addpanel panel" ng-hide="toggle">
				<button ng-click="toggle = !toggle" class="btn-panel">Show All Users</button><br/><br/>
				<table class="table table-bordered table-striped container">
					<tr>
						<td>First Name:</td>
						<td><input type="text" ng-model="user.firstName"/></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><input type="text" ng-model="user.lastName"/></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" ng-model="user.email"/></td>
					</tr>
					<tr>
						<td>Mobile:</td>
						<td><input type="text" ng-model="user.mobile"/>&nbsp;&nbsp;Count=&nbsp;{{user.mobile.length}}&nbsp;</td>
					</tr>
					<tr>
						<td>City:</td>
						<td><input type="text" ng-model="user.city"/></td>
					</tr>
					<tr>
						<td>State:</td>
						<td><input type="text" ng-model="user.state"/></td>
					</tr>
					<tr>
						<td>User Status:</td>
						<td>
							<select ng-model="user.status" ng-options="status as status for status in statuses">
								<option value="">-- Select --</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><br/><button ng-click="addUser()" class="btn-panel-big">Add New User</button></td>
					</tr>
				</table>								
			</div>
		</div>	
	</body>
</html>