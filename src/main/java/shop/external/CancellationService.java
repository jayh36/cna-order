
package shop.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
// 백엔드에서 서버 투 서버 콜 가능
// req/res 동기호출
//@FeignClient(name="delivery", url="http://delivery:8080") // 클라우드용
//@FeignClient(name="delivery", url="http://localhost:8082") // local용
@FeignClient(name="delivery", url="${api.url.delivery}") // local용
public interface CancellationService {

    @RequestMapping(method= RequestMethod.POST, path="/cancellations") //http://localhost:8082/cancellations 실제 호출
    public void cancel(@RequestBody Cancellation cancellation);

}