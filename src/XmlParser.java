import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlParser {
    static final String VALUTE = "Valute";
    static final String CODE = "Code";
    static final String NAME = "Name";
    static final String VALUE = "Value";
    static final String NOMINAL = "Nominal";
    static final String DESCRIPTION = "Description";
    static final String VALCURS = "ValCurs";

    public List<Currency> readCurrencies(String currencyURL)
    {
        List<Currency> currencies = new ArrayList<Currency>();
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = new URL(currencyURL).openStream();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

            Currency currency = null;
            String description = null;

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    switch (elementName) {
                        case VALCURS:
                            Iterator<Attribute> att = startElement.getAttributes();
                            while(att.hasNext())
                            {
                                Attribute attribute = att.next();
                                if (attribute.getName().toString().equals(DESCRIPTION)) {
                                    description = attribute.getValue();
                                }
                            }
                            break;
                        case VALUTE:
                            currency = new Currency(description);
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(CODE)) {
                                    currency.setCode(attribute.getValue());
                                }
                            }
                            break;
                        case NOMINAL:
                            event = eventReader.nextEvent();
                            currency.setNominal(event.asCharacters().getData());
                            break;
                        case NAME:
                            event = eventReader.nextEvent();
                            currency.setName(event.asCharacters().getData());
                            break;
                        case VALUE:
                            event = eventReader.nextEvent();
                            currency.setChangeRate(event.asCharacters().getData());
                            break;
                    }
                }
                    if (event.isEndElement()) {
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equals(VALUTE)){
                            currencies.add(currency);

                        }
                    }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }

}
