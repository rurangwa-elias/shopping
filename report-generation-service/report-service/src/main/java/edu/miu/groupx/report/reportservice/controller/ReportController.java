package edu.miu.groupx.report.reportservice.controller;


import edu.miu.groupx.report.reportservice.dto.ReportProductDto;
import edu.miu.groupx.report.reportservice.feginClient.ProductProxy;
import edu.miu.groupx.report.reportservice.service.CreateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vendors/{vendorId}/reports")
public class ReportController {

    @Autowired
    private CreateReportService createReportService;

    @Autowired
    ProductProxy productProxy;

    @GetMapping
    public void getOrders(HttpServletResponse response, @PathVariable String vendorId) throws URISyntaxException {

        String pathName = ReportController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        pathName = pathName.replaceAll("/target/classes/.*", "/src/main/resources/viewSalesReports.jrxml");
        Map<String, String> parameters = new HashMap();
//        ReportProductDto p1 = new ReportProductDto(12345, "title", "desc", 123.3, 125, 10);

        List<ReportProductDto> reportProductDtos =  productProxy.salesReportsVendor(Long.parseLong(vendorId));

        System.out.println(reportProductDtos);

//        List<ReportProductDto> dataList = Arrays.asList(p1, p1);
//        String pathname = "E:\\MUM\\9-PM\\0-git-repo\\Goshop-backend\\report\\src\\main\\resources\\templates\\viewSalesReports.jrxml";

//        parameters.put("Id", "sony");
//        parameters.put("Name", "sony");
//        parameters.put("Description", "sony");
//        parameters.put("Price", "sony");
//        parameters.put("StockAmount ", "sony");
//        parameters.put("SoldAmount ", "sony");
        try {

            response = createReportService.createPdfReportSalesVendor(response, reportProductDtos, parameters, pathName);
//            response = factory.getPDF("vendor").createPdfReport(response, parameters, id);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
    }

//    @GetMapping("/user/{userId}")
//    public List<Orders> getOrderByUserId(@PathVariable("userId") long userId){
//        return orderService.getByUserId(userId);
//    }
//
//    @GetMapping("/order/{orderId}")
//    public Optional<Orders> getByOrderId(@PathVariable("orderId") long orderId){
//        return orderService.getByorderId(orderId);
//    }
//
//    @PostMapping
//    public Orders addOrder(@RequestBody Orders order){
//
//
//        List<OrderDetails> orderDetails = order.getOrderDetails();
//
//        Double total = 0.0;
//        for(OrderDetails o : orderDetails){
//        	o.setOrder(order);
//            o.setSubTotal(o.getQuantity() * o.getItemPrice());
//            total =+ o.getSubTotal();
//        }
//
//        order.setOrderDetails(orderDetails);
//        order.setTotalAmount(total);
//
//        return orderService.addOrder(order);
//
//    }
//
//    @DeleteMapping("/remove/{orderId}")
//    public void deleteByOrderid(@PathVariable("orderId") long orderId){
//        orderService.deleteOrderById(orderId);
//    }
//
//    @PutMapping("/update/{orderId}")
//    public Orders updateOrder(@PathVariable("orderId") long orderId, @RequestBody Orders order){
//        return orderService.updateOrder(orderId, order);
//    }
}
