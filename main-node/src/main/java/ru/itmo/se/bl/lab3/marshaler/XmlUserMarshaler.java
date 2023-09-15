package ru.itmo.se.bl.lab3.marshaler;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.itmo.se.bl.lab3.entity.User;
import ru.itmo.se.bl.lab3.xml.UsersXmlEntity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class XmlUserMarshaler {
    private static final String USERS_XML_FILEPATH = "users.xml";

    public static void writeToXML(List<User> userList) throws JAXBException {
        UsersXmlEntity usersXmlEntity = new UsersXmlEntity(userList);
        JAXBContext context = JAXBContext.newInstance(UsersXmlEntity.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(usersXmlEntity, new File(USERS_XML_FILEPATH));
    }

    public static List<User> readFromXML() throws JAXBException, IOException {
        Path p = Path.of(USERS_XML_FILEPATH);

        if (Files.exists(p)) {
            JAXBContext context = JAXBContext.newInstance(UsersXmlEntity.class);

            return Optional.ofNullable(((UsersXmlEntity) context.createUnmarshaller()
                    .unmarshal(new FileReader(USERS_XML_FILEPATH))).getUsers()).orElse(new ArrayList<>());
        }
        else {
            ArrayList<User> users = new ArrayList<>();
            writeToXML(users);

            return users;
        }
    }
}
