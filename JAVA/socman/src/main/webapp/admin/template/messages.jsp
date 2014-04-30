<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Messages: style can be found in dropdown.less-->
<li class="dropdown messages-menu">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <i class="fa fa-envelope"></i>
        <span class="label label-success">{{messages.length}}</span>
    </a>
    <ul class="dropdown-menu">
        <li class="header">You have {{messages.length}} messages</li>
        <li>
            <!-- inner menu: contains the actual data -->
            <ul class="menu">
            	<li ng-repeat="message in messages"><!-- start message -->
                    <a href="#">
                        <div class="pull-left">
                            <img ng-src="{{message.avatar}}" class="img-circle" alt="User Image"/>
                        </div>
                        <h4>
                            {{message.messageFrom}}
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                        </h4>
                        <p>{{message.shortMessage}}</p>
                    </a>
                </li>
               
            </ul>
        </li>
        <li class="footer"><a href="#">See All Messages</a></li>
    </ul>
</li>