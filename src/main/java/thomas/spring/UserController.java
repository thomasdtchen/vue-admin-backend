package thomas.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import thomas.spring.pojo.Result;
import thomas.spring.pojo.User;

@RestController
@CrossOrigin
@RequestMapping("/vue-admin-template/user")
@Slf4j
public class UserController {

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("" + user);
        return Result.ok().data("token", JwtUtils.generateToken(user.getUsername()));
    }

    @GetMapping("/info")
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://up.hczm1.com/edpic_source/c5/bd/b7/c5bdb7cf2909b1a851ef4781a248f43d.jpg";
        return Result.ok().data("name", username).data("avatar", url);
    }

    @PostMapping("/logout")
    public Result logout(){return Result.ok();}


}
