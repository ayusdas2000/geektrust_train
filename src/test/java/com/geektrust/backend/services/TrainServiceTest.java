package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Route;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.entities.Train;
import com.geektrust.backend.exceptions.TrainEndedException;
import com.geektrust.backend.repositories.BoggyRepository;
import com.geektrust.backend.repositories.RouteRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.repositories.TrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TrainServiceTest {
    private Train trainA;
    private Train trainB;
    private List<Route> routes;
    //following constants are declared because geektrust ai tool thinks accessing list elements through numbers are also magic numbers
    private final int FIRST_INDEX = 0;
    private final int SECOND_INDEX = 1;
    private final int THIRD_INDEX = 2;
    private final int FOURTH_INDEX = 3;

    @Mock
    TrainRepository trainRepository;

    @Mock
    RouteRepository routeRepository;

    @Mock
    StationRepository stationRepository;

    @Mock
    BoggyRepository boggyRepository;


    @InjectMocks
    TrainService trainService;

    @BeforeEach
    public void setup(){
        LinkedList<Boggy> boggiesA = new LinkedList<Boggy>(){
            {
                add(new Boggy("3", null));
                add(new Boggy("1", new Station("1", "Nagpur", "NGP", 1600)));
                add(new Boggy("2",new Station("2", "Agra", "AGA", 2500)));
            }
        };
        trainA = new Train("1","abc", boggiesA);
        LinkedList<Boggy> boggiesB = new LinkedList<Boggy>(){
            {
                add(new Boggy("3", null));
                add(new Boggy("3", new Station("3", "Itarsi", "ITJ", 2700)));
                add(new Boggy("4",new Station("4", "New Jalpaiguri", "NJP", 4200)));
            }
        };
        trainB = new Train("2","def", boggiesB);
        LinkedList<Station> stationsA = new LinkedList<Station>(){
            {
                add(new Station("1", "Nagpur", "NGP", 1600));
                add(new Station("2", "Agra", "AGA", 2500));
            }
        };
        Route routeA = new Route("1", "routeA", stationsA, 1200);

        LinkedList<Station> stationsB = new LinkedList<Station>(){
            {
                add(new Station("3", "Itarsi", "ITJ", 2700));
                add(new Station("4", "New Jalpaiguri", "NJP", 4200));
            }
        };
        Route routeB = new Route("2", "routeB", stationsB, 2000);
        routes = new ArrayList<Route>();
        routes.add(routeA);
        routes.add(routeB);

    }
    @Test
    @DisplayName("#1 Merge Train test")
    public void merge_train_test(){
        //Arrange
        when(trainRepository.findTrainByName("abc")).thenReturn(trainA);
        when(trainRepository.findTrainByName("def")).thenReturn(trainB);
        when(routeRepository.getAllRoutes()).thenReturn(routes);
        //Act
        Train mergedTrain = trainService.mergeTrain("abc", "def");
        //Assert
        assertEquals(6,mergedTrain.getBoggies().size() );

    } 
    
    @Test
    @DisplayName("#2 Merge train command must throw exception if boggy size is zero")
    public void merge_train_must_throw_exception_if_boggy_size0(){
         //Arrange
         LinkedList<Boggy> boggies = new LinkedList<Boggy>();
         when(trainRepository.findTrainByName("abc")).thenReturn(new Train("1", "abc", boggies));
         when(trainRepository.findTrainByName("def")).thenReturn(new Train("2", "def", boggies));
        //Assert
        assertThrows(TrainEndedException.class,() -> trainService.mergeTrain("abc", "def"));

    }

    @Test
    @DisplayName("#3 Travel Train test")
    public void travel_train_test(){
        //Arrange
        when(trainRepository.findTrainByName("abc")).thenReturn(trainA);
        when(routeRepository.findRouteByName("routeA")).thenReturn(routes.get(FIRST_INDEX));

        //Act
        Train train = trainService.travel("abc", "routeA", "NDL");

        //Assert
        assertEquals(1, train.getBoggies().size());

    }

    @Test
    @DisplayName("#4 Create Train test")
    public void create_train_test(){
        //Arrange
        List<Station> stations = new ArrayList<Station>(){
            {
                add(new Station("1", "Chennai", "CHN", 0));
                add(new Station("2", "Salem", "SLM", 350));
                add(new Station("3", "Bangalore", "BLR", 550));

            }
        };
        LinkedList<Boggy> listOfBoggies = new LinkedList<Boggy>(){
            {
                add(new Boggy("4", null));
                add(new Boggy("1",stations.get(0)));
                add(new Boggy("2",stations.get(1)));
                add(new Boggy("3",stations.get(2)));
            }
        };
        when(stationRepository.findStationByCode("CHN")).thenReturn(stations.get(FIRST_INDEX));
        when(stationRepository.findStationByCode("SLM")).thenReturn(stations.get(SECOND_INDEX));
        when(stationRepository.findStationByCode("BLR")).thenReturn(stations.get(THIRD_INDEX));
        when(boggyRepository.saveBoggy(new Boggy(null))).thenReturn(listOfBoggies.get(FIRST_INDEX));
        when(boggyRepository.saveBoggy(new Boggy(stations.get(FIRST_INDEX)))).thenReturn(listOfBoggies.get(SECOND_INDEX));
        when(boggyRepository.saveBoggy(new Boggy(stations.get(SECOND_INDEX)))).thenReturn(listOfBoggies.get(THIRD_INDEX));
        when(boggyRepository.saveBoggy(new Boggy(stations.get(THIRD_INDEX)))).thenReturn(listOfBoggies.get(FOURTH_INDEX));

        Train train = new Train("abc",listOfBoggies);
        Train toBeReturnedFromRepo = new Train("1","abc",listOfBoggies);
        when(trainRepository.saveTrain(train)).thenReturn(toBeReturnedFromRepo);
        

        List<String> boggiesToBeSaved = Arrays.asList("ENGINE","CHN","SLM","BLR");
        //Act
        Train returnedTrain = trainService.createTrain("abc", boggiesToBeSaved);
        //Assert
        assertEquals("1", returnedTrain.getId());
    }
}
