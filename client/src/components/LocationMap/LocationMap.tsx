import { useRef, useEffect } from "react";
import L from "leaflet";
import { Position } from "../../models/Position";
import Logo from "../../assets/icons/tripmatefavicon.svg";
import your_location from "../../assets/icons/yourLocation.png";
import "leaflet/dist/leaflet.css";

interface Props {
  latitude: number;
  longitude: number;
  title: string;
  yourLocation?: Position;
}

function LocationMap({ latitude, longitude, title, yourLocation }: Props) {
  const mapRef = useRef<HTMLDivElement>(null);
  const mapi = useRef<L.Map>();

  const locateUserIcon = new L.Icon({
    iconUrl: your_location,
    iconRetinaUrl: your_location,
    iconSize: [32, 32], // tamaño del icono en píxeles
    iconAnchor: [16, 32], // posición del punto que se utiliza para anclar el icono al mapa
    popupAnchor: [0, -32], // posición del punto que se utiliza para anclar el contenido emergente al icono
  });

  const locateExperienceIcon = new L.Icon({
    iconUrl: Logo,
    iconRetinaUrl: Logo,
    iconSize: [24, 24],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32],
  });

  useEffect(() => {
    if (mapRef.current) {
      // Create the map instance
      mapi.current = L.map(mapRef.current).setView([latitude, longitude], 12);

      // Add the tile layer
      L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
        attribution: "Map data &copy; OpenStreetMap contributors",
        maxZoom: 17,
      }).addTo(mapi.current);

      if (yourLocation?.coords.latitude) {
        L.circle(
          [yourLocation.coords.latitude, yourLocation.coords.longitude],
          {
            color: "red",
            fillColor: "#f63b553d",
            fillOpacity: 0.2,
            radius: 4000,
          }
        ).addTo(mapi.current);

        const marker = L.marker(
          [yourLocation.coords.latitude, yourLocation.coords.longitude],
          {
            icon: locateUserIcon,
          }
        ).addTo(mapi.current);
        marker.bindPopup("Estas aqui").openPopup();
      }

      // Add the marker
      const marker = L.marker([latitude, longitude], {
        icon: locateExperienceIcon,
      }).addTo(mapi.current);

      // Add a popup to the marker
      marker.bindPopup(title).openPopup();
    }

    return () => {
      mapi.current?.remove();
    };
  }, [latitude, longitude]);

  return (
    <div className="flex justify-center w-full h-full sm:rounded-xl">
      <div
        id="map"
        className="map flex w-full h-full sm:rounded-xl"
        ref={mapRef}
      />
    </div>
  );
}

export default LocationMap;
