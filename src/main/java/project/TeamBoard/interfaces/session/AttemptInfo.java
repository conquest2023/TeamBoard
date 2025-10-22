package project.TeamBoard.interfaces.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptInfo {

  public   int count;

  public   Instant firstFailureAt;

  public   Instant lastFailureAt;


  public  Instant lockUntil;
}
