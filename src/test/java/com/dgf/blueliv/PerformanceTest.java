package com.dgf.blueliv;

import com.dgf.blueliv.search.FileSearch;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

import static com.dgf.blueliv.search.FileSearch.*;
import static org.testng.Assert.assertEquals;

/**
 * Generates a big file with names & cities in list. Id's are randomly generated.
 */
public class PerformanceTest {

    public static final String FILE = "file.test.performance.txt";

    private static final String[] names = ("OLIVIA,RUBY,EMILY,GRACE,JESSICA,CHLOE,SOPHIE,LILY,AMELIA,MIA,ELLA,CHARLOTTE,LUCY," +
            "MEGAN,ELLIE,ISABELLE,ISABELLA,HANNAH,KATIE,AVA,HOLLY,SUMMER,MILLIE,DAISY,PHOEBE,FREYA,ABIGAIL,POPPY,ERIN,EMMA," +
            "MOLLY,IMOGEN,AMY,JASMINE,ISLA,SCARLETT,LEAH,SOPHIA,ELIZABETH,EVA,BROOKE,MATILDA,CAITLIN,KEIRA,ALICE,LOLA,LILLY," +
            "AMBER,ISABEL,LAUREN,GEORGIA,GRACIE,ELEANOR,BETHANY,MADISON,AMELIE,ISOBEL,PAIGE,LACEY,SIENNA,LIBBY,MAISIE,ANNA"+
            "REBECCA,ROSIE,TIA,LAYLA,MAYA,NIAMH,ZARA,SARAH,LEXI,MADDISON,ALISHA,SOFIA,SKYE,NICOLE,LEXIE,FAITH,MARTHA,HARRIET," +
            "ZOE,EVE,JULIA,AIMEE,HOLLIE,LYDIA,EVELYN,ALEXANDRA,MARIA,FRANCESCA,TILLY,FLORENCE,ALICIA,ABBIE,EMILIA,COURTNEY," +
            "MARYAM,ESME,JACK,OLIVER,THOMAS,HARRY,JOSHUA,ALFIE,CHARLIE,DANIEL,JAMES,WILLIAM,SAMUEL,GEORGE,JOSEPH,LEWIS,ETHAN," +
            "MOHAMMED,DYLAN,BENJAMIN,ALEXANDER,JACOB,RYAN,LIAM,JAKE,MAX,LUKE,TYLER,CALLUM,MATTHEW,JAYDEN,OSCAR,ARCHIE,ADAM," +
            "RILEY,HARVEY,HARRISON,LUCAS,MUHAMMAD,HENRY,ISAAC,LEO,CONNOR,EDWARD,FINLEY,LOGAN,NOAH,CAMERON,ALEX,OWEN,RHYS," +
            "NATHAN,JAMIE,MICHAEL,MASON,TOBY,AARON,CHARLES,BEN,THEO,LOUIS,FREDDIE,FINLAY,LEON,HARLEY,DAVID,MOHAMMAD,REECE," +
            "KIAN,KAI,KYLE,BRANDON,HAYDEN,ZACHARY,KIERAN,LUCA,ASHTON,BAILEY,SEBASTIAN,GABRIEL,SAM,EVAN,BRADLEY,ELLIOT,JOHN," +
            "TAYLOR,JOE,COREY,REUBEN,JOEL,ROBERT,ELLIS,BLAKE,AIDAN,LOUIE,CHRISTOPHER,EWAN,JAY,MORGAN,BILLY,SEAN,ZAK")
            .split(",");

    private static final String[] cities = ("Istanbul,Moscow,London,Saint Petersburg,Berlin,Kyiv,Madrid,Rome,Paris,Bucharest," +
            "Minsk,Hamburg,Vienna,Warsaw,Budapest,Barcelona,Kharkiv,Munich,Milan,Prague,Nizhny Novgorod,Kazan,Sofia,Birmingham," +
            "Brussels,Samara,Belgrade,UfaRostov-on-Don,Cologne,Voronezh,Perm,Volgograd,Odessa,Krasnodar"+
            //US
            "New York,Los Angeles,Chicago,Illinois,Houston,Philadelphia,Phoenix,San Antonio,San Diego,Dallas,San Jose,Austin," +
            "Indianapolis,Jacksonville,San Francisco,Columbus,Charlotte,Fort Worth,Detroit,El Paso,Memphis,Seattle,Denver,Washington," +
            "Boston,Nashville-Davidson,Baltimore,Oklahoma City,Louisville,Portland,Las Vegas,Milwaukee,Albuquerque,Tucson,Fresno,Sacramento," +
            "Long Beach,Kansas City,Mesa,Virginia Beach,Atlanta,Colorado Springs,Omaha,Raleigh,Miami,Oakland,Minneapolis,Tulsa,Cleveland," +
            "Wichita,Arlington,New Orleans,Bakersfield,Tampa,Honolulu,Aurora,Anaheim,Santa Ana,St. Louis,Riverside,Corpus Christi," +
            "Lexington-Fayette,Pittsburgh,Anchorage,Stockton,Cincinnati,St. Paul,Toledo,Greensboro,Newark,Plano,Henderson,Lincoln," +
            "Buffalo,Jersey City,Chula Vista,Fort Wayne,Orlando,St. Petersburg,Chandler,Laredo,Norfolk,Durham,Madison,Lubbock," +
            "Irvine,Winston-Salem,Glendale,Garland,Hialeah,Reno,Chesapeake,Gilbert,Baton Rouge,Irving,Scottsdale,North Las Vegas," +
            "Fremont,Boise City,Richmond,San Bernardino,Birmingham,Spokane,Rochester,Des Moines,Modesto,Fayetteville,Tacoma," +
            "Oxnard,Fontana,Columbus,Montgomery,Moreno Valley,Shreveport,Aurora,Yonkers,Akron,Huntington Beach,Little Rock," +
            "Augusta-Richmond County,Amarillo,Glendale,Mobile,Grand Rapids,Salt Lake City,Tallahassee,Huntsville,Grand Prairie," +
            "Knoxville,Worcester,Newport News,Brownsville,Overland Park,Santa Clarita,Providence,Garden Grove,Chattanooga," +
            "Oceanside,Jackson,Fort Lauderdale,Santa Rosa,Rancho Cucamonga,Port St. Lucie,Tempe,Ontario,Vancouver,Cape Coral," +
            "Sioux Falls,Springfield,Peoria,Pembroke Pines,Elk Grove,Salem,Lancaster,Corona,Eugene,Palmdale,Salinas,Springfield," +
            "Pasadena,Fort Collins,Hayward,Pomona,Cary,Rockford,Alexandria,Escondido,McKinney,Kansas City,Joliet,Sunnyvale," +
            "Torrance,Bridgeport,Lakewood,Hollywood,Paterson,Naperville,Syracuse,Mesquite,Dayton,Savannah,Clarksville,Orange," +
            "Pasadena,Fullerton,Killeen,Frisco,Hampton,McAllen,Warren,Bellevue,West Valley City,Columbia,Olathe,Sterling Heights," +
            "New Haven,Miramar,Waco,Thousand Oaks,Cedar Rapids,Charleston,Visalia,Topeka,Elizabeth,Gainesville,Thornton,Roseville," +
            "Carrollton,Coral Springs,Stamford,Simi Valley,Concord,Hartford,Kent,Lafayette,Midland,Surprise,Denton,Victorville," +
            "Evansville,Santa Clara,Abilene,Athens-Clarke County,Vallejo,Allentown,Norman,Beaumont,Independence,Murfreesboro," +
            "Ann Arbor,Springfield,Berkeley,Peoria,Provo,El Monte,Columbia,Lansing,Fargo,Downey,Costa Mesa,Wilmington,Arvada," +
            "Inglewood,Miami Gardens,Carlsbad,Westminster,Rochester,Odessa,Manchester,Elgin,West Jordan,Round Rock,Clearwater," +
            "Waterbury,Gresham,Fairfield,Billings,Lowell,San Buenaventura,Pueblo,High Point,West Covina,Richmond,Murrieta," +
            "Cambridge,Antioch,Temecula,Norwalk,Centennial,Everett,Palm Bay,Wichita Falls,Green Bay,Daly City,Burbank,Richardson," +
            "Pompano Beach,North Charleston,Broken Arrow,Boulder,West Palm Beach,Santa Maria,El Cajon,Davenport,Rialto,Las Cruces," +
            "San Mateo,Lewisville,South Bend,Lakeland,Erie,Tyler,Pearland,College Station,Kenosha,Sandy Springs,Clovis,Flint," +
            "Roanoke,Albany,Jurupa Valley,Compton,San Angelo,Hillsboro,Lawton,Renton,Vista,Davie,Greeley,Mission Viejo,Portsmouth," +
            "Dearborn,South Gate,Tuscaloosa,Livonia,New Bedford,Vacaville,Brockton,Roswell,Beaverton,Quincy,Sparks,Yakima," +
            "Lee's Summit,Federal Way,Carson,Santa Monica,Hesperia,Allen,Rio Rancho,Yuma,Westminster,Orem,Lynn,Redding,Spokane Valley," +
            "Miami Beach,League City,Lawrence,Santa Barbara,Plantation,Sandy,Sunrise,Macon,Longmont,Boca Raton,San Marcos," +
            "Greenville,Waukegan,Fall River,Chico,Newton,San Leandro,Reading,Norwalk,Fort Smith,Newport Beach,Asheville,Nashua," +
            "Edmond,Whittier,Nampa,Bloomington,Deltona,Hawthorne,Duluth,Carmel,Suffolk,Clifton,Citrus Heights,Livermore,Tracy," +
            "Alhambra,Kirkland,Trenton,Ogden,Hoover,Cicero,Fishers,Sugar Land,Danbury,Meridian,Indio,Concord,Menifee,Champaign," +
            "Buena Park,Troy,O'Fallon,Johns Creek,Bellingham,Westland,Bloomington,Sioux City,Warwick,Hemet,Longview,Farmington Hills," +
            "Bend,Lakewood,Merced,Mission,Chino,Redwood City,Edinburg,Cranston,Parma,New Rochelle,Lake Forest,Napa,Hammond," +
            "Fayetteville,Bloomington,Avondale,Somerville,Palm Coast,Bryan,Gary,Largo,Brooklyn Park,Tustin,Racine,Deerfield Beach," +
            "Lynchburg,Mountain View,Medford,Lawrence,Bellflower,Melbourne,St. Joseph,Camden,St. George,Kennewick,Baldwin Park," +
            "Chino Hills,Alameda,Albany,Arlington Heights,Scranton,Evanston,Kalamazoo,Baytown,Upland,Springdale,Bethlehem,Schaumburg," +
            "Mount Pleasant,Auburn,Decatur,San Ramon,Pleasanton,Wyoming,Lake Charles,Plymouth,Bolingbrook,Pharr,Appleton,Gastonia," +
            "Folsom,Southfield,Rochester Hills,New Britain,Goodyear,Canton,Warner Robins,Union City,Perris,Manteca,Iowa City," +
            "Jonesboro,Wilmington,Lynwood,Loveland,Pawtucket,Boynton Beach,Waukesha,Gulfport,Apple Valley,Passaic,Rapid City,Layton," +
            "Lafayette,Turlock,Muncie,Temple,Missouri City,Redlands,Santa Fe,Lauderhill,Milpitas,Palatine,Missoula,Rock Hill,Jacksonville," +
            "Franklin,Flagstaff,Flower Mound,Weston,Waterloo,Union City,Mount Vernon,Fort Myers,Dothan,Rancho Cordova,Redondo Beach," +
            "Jackson,Pasco,St. Charles,Eau Claire,North Richland Hills,Bismarck,Yorba Linda,Kenner,Walnut Creek,Frederick,Oshkosh," +
            "Pittsburg,Palo Alto,Bossier City,Portland,St. Cloud,Davis,South San Francisco,Camarillo,North Little Rock,Schenectady," +
            "Gaithersburg,Harlingen,Woodbury,Eagan,Yuba City,Maple Grove,Youngstown,Skokie,Kissimmee,Johnson City,Victoria," +
            "San Clemente,Bayonne,Laguna Niguel,East Orange,Shawnee,Homestead,Rockville,Delray Beach,Janesville,Conway,Pico Rivera," +
            "Lorain,Montebello,Lodi,New Braunfels,Marysville,Tamarac,Madera,Conroe,Santa Cruz,Eden Prairie,Cheyenne,Daytona Beach," +
            "Alpharetta,Hamilton,Waltham,Coon Rapids,Haverhill,Council Bluffs,Taylor,Utica,Ames,La Habra,Encinitas,Bowling Green," +
            "Burnsville,Greenville,West Des Moines,Cedar Park,Tulare,Monterey Park,Vineland,Terre Haute,North Miami,Mansfield," +
            "West Allis,Bristol,Taylorsville,Malden,Meriden,Blaine,Wellington,Cupertino,Springfield,Rogers,St. Clair Shores," +
            "Gardena,Pontiac,National City,Grand Junction,Rocklin,Chapel Hill,Casper,Broomfield,Petaluma,South Jordan,Springfield," +
            "Great Falls,Lancaster,North PortLakewood,Marietta,San Rafael,Royal Oak,Des Plaines,Huntington Park,La Mesa,Orland Park," +
            "Auburn,Lakeville,Owensboro,Moore,Jupiter,Idaho Falls,Dubuque,Bartlett,Rowlett,Novi,White Plains,Arcadia,Redmond,Lake Elsinore," +
            "Ocala,Tinley Park,Port Orange,Medford,Oak Lawn,Rocky Mount,Kokomo,Coconut Creek,Bowie,Berwyn,Midwest City,Fountain Valley,Buckeye," +
            "Dearborn Heights,Woodland,Noblesville,Valdosta,Diamond Bar,Manhattan,Santee,Taunton,Sanford,Kettering,New Brunswick,Decatur," +
            "Chicopee,Anderson,Margate,Weymouth Town,Hempstead,Corvallis,Eastvale,Porterville,West Haven,Brentwood,Paramount,Grand Forks," +
            "Georgetown,St. Peters,Shoreline,Mount Prospect,Hanford,Rosemead,Lehi,Pocatello,Highland,Novato,Port Arthur,Carson City," +
            "San Marcos,Hendersonville,Elyria,Revere,Pflugerville,Greenwood,Bellevue,Wheaton,Smyrna,Sarasota,Blue Springs,Colton," +
            "Euless,Castle Rock,Cathedral City,Kingsport,Lake Havasu City,Pensacola,Hoboken,Yucaipa,Watsonville,Richland,Delano," +
            "Hoffman Estates,Florissant,Placentia,West New York,Dublin,Oak Park,Peabody,Perth Amboy,Battle Creek,Bradenton,Gilroy," +
            "Milford,Albany,Ankeny,La Crosse,Burlington,DeSoto,Harrisonburg,Minnetonka,Elkhart,Lakewood,Glendora,Southaven,Charleston," +
            "Joplin,Enid,Palm Beach Gardens,Plainfield,Grand Island,Palm Desert,Huntersville,Tigard,Lenexa,Saginaw,Kentwood," +
            "Doral,Apple Valley,Grapevine,Aliso Viejo,Sammamish,Casa Grande,Pinellas Park,Troy,West Sacramento,Burien,Commerce City," +
            "Monroe,Cerritos,Downers Grove,Coral Gables,Wilson,Niagara Falls,Poway,Edina,Cuyahoga Falls,Rancho Santa Margarita,Harrisburg," +
            "Huntington,La Mirada,Cypress,Caldwell,Logan,Galveston,Sheboygan,Middletown,Murray,Roswell,Parker,Bedford,East Lansing," +
            "Methuen,Covina,Alexandria,Olympia,Euclid,Mishawaka,Salina,Azusa,Newark,Chesterfield,Leesburg,Dunwoody,Hattiesburg," +
            "Roseville,Bonita Springs,Portage,St. Louis Park,Collierville,Middletown,Stillwater,East Providence,Lawrence,Wauwatosa,Mentor," +
            "Ceres,Cedar Hill,Mansfield,Binghamton,Coeur d'Alene,San Luis Obispo,Minot,Palm Springs,Pine Bluff,Texas City,Summerville," +
            "Twin Falls,Jeffersonville,San Jacinto,Madison,Altoona,Columbus,Beavercreek,Apopka,Elmhurst,Maricopa,Farmington,Glenview," +
            "Cleveland Heights,Draper,Lincoln,Sierra Vista,Lacey,Biloxi,Strongsville,Barnstable Town,Wylie,Sayreville,Kannapolis,Charlottesville," +
            "Littleton,Titusville,Hackensack,Newark,Pittsfield,York,Lombard,Attleboro,DeKalb,Blacksburg,Dublin,Haltom City,Lompoc,El Centro," +
            "Danville,Jefferson City,Cutler Bay,Oakland Park,North Miami Beach,Freeport,Moline,Coachella,Fort Pierce,Smyrna,Bountiful," +
            "Fond du Lac,Everett,Danville,Keller,Belleville,Bell Gardens,Cleveland,North Lauderdale,Fairfield,Salem,Rancho Palos Verdes," +
            "San Bruno,Concord,Burlington,Apex,Midland,Altamonte Springs,Hutchinson,Buffalo Grove,Urbandale,State College,Urbana,Plainfield," +
            "Manassas,Bartlett,Kearny,Oro Valley,Findlay,Rohnert Park,Westfield,Linden,Sumter,Wilkes-Barre,Woonsocket,Leominster,Shelton," +
            "Brea,Covington,Rockwall,Meridian,Riverton,St. Cloud,Quincy,Morgan Hill,Warren,Edmonds,Burleson,Beverly,Mankato,Hagerstown," +
            "Prescott,Campbell,Cedar Falls,Beaumont,La Puente,Crystal Lake,Fitchburg,Carol Stream,Hickory,Streamwood,Norwich,Coppell," +
            "San Gabriel,Holyoke,Bentonville,Florence,Brentwood,Bozeman,New Berlin,Goose Creek,Huntsville,Prescott Valley,Maplewood," +
            "Romeoville,Duncanville,Atlantic City,Clovis,The Colony,Culver City,Marlborough,Hilton Head Island,Moorhead,Calexico," +
            "Bullhead City,Germantown,La Quinta,Lancaster,Wausau,Sherman,Ocoee,Shakopee,Woburn,Bremerton,Rock Island,Muskogee,Cape Girardeau," +
            "Annapolis,Greenacres,Ormond Beach,Hallandale Beach,Stanton,Puyallup,Pacifica,Hanover Park,Hurst,Lima,Marana,Carpentersville,Oakley," +
            "Huber Heights,Lancaster,Montclair,Wheeling,Brookfield,Park Ridge,Florence,Roy,Winter Garden,Chelsea,Valley Stream,Spartanburg," +
            "Lake Oswego,Friendswood,Westerville,Northglenn,Phenix City,Grove City,Texarkana,Addison,Dover,Lincoln Park,Calumet City,Muskegon," +
            "Aventura,Martinez,Greenfield,Apache Junction,Monrovia,Weslaco,Keizer,Spanish Fork,Beloit,Panama City"
    ).toUpperCase().split(",");

    @BeforeSuite
    public void generateBigFile() throws IOException {
        if (!new File(FILE).exists()) {
            Random rnd = new Random();
            try (FileWriter fw = new FileWriter(FILE)) {
                fw.append(F1 + "\n");
                for (String name1 : names) {
                    for (String name2 : names) {
                        String name = name1 + " " + name2;
                        String id = String.valueOf(rnd.nextInt(99999999)) + (char) (rnd.nextInt(26) + 'A');
                        for (String city : cities) {
                            fw.append("D ").append(name).append(",").append(city).append(",").append(id).append("\n");
                        }
                    }
                }
            }
        }
    }

    @Test(timeOut = 30000L)
    public void citySearch() throws IOException {
        System.out.println(FileSearch.getInstance(CITY).find(FILE,"BARCELONA"));
    }
}
