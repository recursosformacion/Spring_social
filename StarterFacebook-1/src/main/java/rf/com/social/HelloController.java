package rf.com.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FriendList;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HelloController {
	 	private final Facebook facebook;
	    private final ConnectionRepository connectionRepository;

	    @Autowired
	    public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
	        this.facebook = facebook;
	        this.connectionRepository = connectionRepository;
	    }

	    @GetMapping
		public String getfacebookFeeds(Model model) {
			if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
				return "redirect:/connect/facebook";
			}
			//List<FriendList> friends = facebook.friendOperations().getFriendLists();
			PagedList<Post> posts = facebook.feedOperations().getPosts();
			model.addAttribute("profileName", posts.get(0).getFrom().getName());
			model.addAttribute("posts", posts);
			model.addAttribute("friends",facebook.friendOperations().getFriends());
			return "profile";
		}
	    
	    @PostMapping
	    public String sendMessage(@RequestBody String mensaje) {
	    	if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
				return "redirect:/connect/facebook";
			}
	    	facebook.feedOperations().updateStatus("El mensaje era:" + mensaje);
	    	return "redirect:/";
	    }

	}
