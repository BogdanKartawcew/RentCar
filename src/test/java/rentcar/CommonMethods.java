package rentcar;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class CommonMethods {

    public IDataSet tablesXml() throws Exception {
        IDataSet[] datasets = new IDataSet[] {
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("availability.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("car.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("car_image.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("client.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("history.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("persistent_logins.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("received_reservations.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("reservation_status.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("role.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("user.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("user_image.xml")),
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("user_role.xml")),
        };
        return new CompositeDataSet(datasets);
    }
}
