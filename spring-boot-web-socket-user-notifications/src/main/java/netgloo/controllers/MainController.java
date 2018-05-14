package netgloo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.Notification;
import netgloo.services.NotificationService;

@Controller
public class MainController {

  @Autowired
  private NotificationService notificationService;

  /**
   * GET  /  -> show the index page.
   */
  @RequestMapping("/")
  public String index() {
    return "index";
  }

  /**
   * GET  /notifications  -> show the notifications page.
   */
  @RequestMapping("/notifications")
  public String notifications() {
    return "notifications";
  }

  /**
   * POST  /some-action  -> do an actinotifications.htmlon.
   * 
   * After the action is performed will be notified UserA.
   */
  @RequestMapping(value = "/some-action", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> someAction(@RequestBody Notification notification) {
   
	  
    // Do an action here
    // ...
    // Send the notification to "UserA" (by username)
    notificationService.notify(
      new Notification(notification.getContent()), // notification object
      "UserA"                    // username
    );
    
    // Return an http 200 status code
    return new ResponseEntity<>(HttpStatus.OK);
  }

} // class MainController
