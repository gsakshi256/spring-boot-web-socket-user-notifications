package netgloo.eventlistener;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	@Autowired
	Publisher publisher;

	@RequestMapping("/send")
	public String sendMsg(@RequestParam("msg") String msg) throws IOException, Exception {
		publisher.produceMsg(msg);
		return "Done";

	}
}
