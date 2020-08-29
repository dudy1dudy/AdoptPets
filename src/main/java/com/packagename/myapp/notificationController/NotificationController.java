package com.packagename.myapp.notificationController;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.notification.Notification;

public class NotificationController {

    public NotificationController(){

    }

    Notification notification=new Notification();
    public Notification makeNotification(){
        H5 text=new H5("Please login");
        notification.add(text);
        notification.setDuration(3000);
        notification.setPosition(Notification.Position.TOP_CENTER);

        return notification;
    }


}
