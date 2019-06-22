package room.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import room.management.bean.Room;


@RestController
public class RoomController {

    @Autowired
    RoomController itemService;

    private List<Room> rooms;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAllItems")
    @ResponseBody
    public ResponseEntity<List<Room>> getAllItems() {
        //Reading all items (ADMIN only can access this)
        List<Room> items = this.rooms;
        System.out.println("Reading items: "+items);
        return new ResponseEntity<List<Room>>(items, HttpStatus.OK);
    }

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello User!";
    }

}
