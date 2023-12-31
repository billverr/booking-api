// package com.booking.api;
//
// import java.io.IOException;
// import java.util.Arrays;
// import java.util.logging.Logger;
//
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
//
// @Validated
// @RestController
// public class MainController {
// private static final Logger log =
// Logger.getLogger(MainController.class.getName());
// private static final String BASE_URI = "/reserve";
//
// @GetMapping(value = { BASE_URI })
// public @ResponseBody void reserve(@RequestParam(required = true) String
// orgId, HttpServletRequest req,
// HttpServletResponse resp) {
// String jspPath = null;
// if ("PYLI".equals(orgId)) {
// jspPath = "/PyliCafe";
// }
// try {
// req.getRequestDispatcher(jspPath + "/index.jsp").include(req, resp);
// } catch (ServletException | IOException e) {
// log.severe(e.getMessage() + "::" + e + "\n" +
// Arrays.toString(e.getStackTrace()));
// }
//
// }
//
// }
