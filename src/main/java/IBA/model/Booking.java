package IBA.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private User user;
    private Flight flight;
    private List<Person> passengers;

}
