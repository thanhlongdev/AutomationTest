package vn.asm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Staff {

    private String id;
    private String name;
    private boolean gender;
    private String birthday;
    private String email;
    private String phone;
    private double salary;

}
