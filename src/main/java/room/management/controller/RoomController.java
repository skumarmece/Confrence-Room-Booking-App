package room.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import room.management.room.bean.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    RoomController itemService;

    public static List<Room> rooms;
    static{
        rooms = new ArrayList<>(Arrays.asList(new Room(1,"Spring Boot in Action","Books"),
                new Room(2,"Java 8 in Action","Books"),
                new Room(3,"Data Structures","Books"),
                new Room(4,"Spring Boot Security","Books")));
    }

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
