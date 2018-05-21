package netgloo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.Notification;
import netgloo.eventlistener.Publisher;
import netgloo.eventlistener.Subcriber;
import netgloo.eventlistener.WebSocketEventListener;
import netgloo.services.NotificationService;

@Controller
public class MainController {

	@Autowired
	private NotificationService notificationService;
	@Autowired
	Publisher publisher;
	@Autowired
	WebSocketEventListener web;
	@Autowired
	Subcriber subcriber;

	/**
	 * GET / -> show the index page.
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * GET /notifications -> show the notifications page.
	 */
	@RequestMapping("/notifications")
	public String notifications() {
		return "notifications";
	}

	/**
	 * POST /some-action -> do an actinotifications.htmlon.
	 * 
	 * After the action is performed will be notified UserA.
	 * 
	 * @throws Exception
	 * @throws IOException
	 */

	@RequestMapping(value = "/some-action", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> someAction(@RequestBody Notification notification) throws IOException, Exception {

		// Do an action here
		// ...
		// Send the notification to "UserA" (by username)
		publisher.produceMsg(notification.getMessage());
		String username = notification.getUser();
		notificationService.notify(new Notification(notification.getMessage()), // notification object
				username // username
		);
		// subcriber.consumer();
		// Return an http 200 status code
		return new ResponseEntity<>(HttpStatus.OK);
	}

} // class MainController
