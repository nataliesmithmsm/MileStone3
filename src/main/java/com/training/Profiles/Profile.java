package com.training.Profiles;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@EntityScan
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ProfileDetails")
public class Profile {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    private boolean isFirstNameEmpty() {
        return firstName == null;
    }

    private boolean isLastNameEmpty() {
        return lastName == null;
    }

//    public boolean isEmpty() {
//        return isFirstNameEmpty() && isLastNameEmpty();
//    }

    @Override
    public String toString() {
         return getId() + " " + getFirstName() + " " + getLastName();
    }
}
