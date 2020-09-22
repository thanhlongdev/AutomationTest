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
public class Response {

    private String name;
    private String testType;
    private String priority;
    private String id;
    private String action;
    private String expectedResult;
    private String actualResult;
    private boolean status;
    private String tester;
    private String date;

}
