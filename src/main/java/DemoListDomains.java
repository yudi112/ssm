

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.rmi.ServerException;
import java.util.List;

/**
 * Created by hasee on 2017/8/21.
 */
public class DemoListDomains {
    private static IAcsClient client = null;
    static {
        String regionId = "cn-hangzhou"; //必填固定值，必须为“cn-hanghou”
        String accessKeyId = "LTAIJhL2Q4BQoxkJ"; // your accessKey
        String accessKeySecret = "ZbM6s4JhppnGhldSXICQzTnFfgBqOX";// your accessSecret
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }
    public static void main(String[] args) {
    //    DescribeDomainsRequest request = new DescribeDomainsRequest();
   //     DescribeDomainsResponse response;
        DescribeDomainRecordsRequest request =new DescribeDomainRecordsRequest();
        request.setDomainName("zhaichenlei.com");
        request.setRRKeyWord("www");
        DescribeDomainRecordsResponse response;
//        describeRegionsRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
//        describeRegionsRequest.setAcceptFormat(FormatType.JSON); //指定api返回格式
//        describeRegionsRequest.setMethod(MethodType.POST); //指定请求方法
//        describeRegionsRequest.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        try {
            response = client.getAcsResponse(request);


            List<DescribeDomainRecordsResponse.Record> list = response.getDomainRecords();
            for (DescribeDomainRecordsResponse.Record domain : list) {
                System.out.println(domain.getRR());
                System.out.println(domain.getValue());
                System.out.println(domain.getRecordId());
                System.out.println(domain.getType());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
