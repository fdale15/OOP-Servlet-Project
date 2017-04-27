package weatherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.forrestdale.predictor.WeatherPredictor;
import com.forrestdale.utils.DateUtil;
import com.forrestdale.utils.WeatherLoader;

/**
 * Created by forrestdale on 4/26/17.
 */
public class predictionServlet extends HttpServlet {
    private static final String FILEPATH = "weatherData.csv";

    private WeatherPredictor mPredictor;

    @Override
    public void init() throws ServletException {
        WeatherLoader wl = new WeatherLoader(FILEPATH, true);
        mPredictor = new WeatherPredictor(wl.getWeatherDays());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String type = req.getParameter("type");
        SimpleDateFormat fmt = new SimpleDateFormat("mm/dd/yyyy");

        if (date == null || type == null) {
            resp.getOutputStream().print("Invalid parameters.");
            return;
        }
        try {
            if (type.equalsIgnoreCase("weekly")) {
                Date dateObj = fmt.parse(date);
                String forecast = "7 Day forecast \n\n\n" + mPredictor.PredictForecastRange(dateObj, DateUtil.deltaDayDate(dateObj, 7)).toString();
                resp.getOutputStream().print(forecast);
            }
            else if (type.equalsIgnoreCase("daily")) {
                String forecast = mPredictor.PredictForecastDay(fmt.parse(date)).toString();
                resp.getOutputStream().print(forecast);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
