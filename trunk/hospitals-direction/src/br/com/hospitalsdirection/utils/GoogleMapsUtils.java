package br.com.hospitalsdirection.utils;

import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

@SuppressLint("HandlerLeak") public class GoogleMapsUtils {

	private Context contexto;
	private GoogleMap mapa;
	private PolylineOptions rectOptions;
	private MarkerOptions marker;

	public GoogleMapsUtils(Context contexto, GoogleMap mapa){
		this.contexto = contexto;
		this.mapa = mapa;
	}

	public void configuraPosicionamento(final LatLng latLng, final Integer tilt, final Integer bearing, final Integer zoom){

		CameraPosition.Builder builder = new CameraPosition

				.Builder()
		.target(latLng != null ? latLng : null)
		.tilt(tilt != null ? tilt : null)
		.bearing(bearing != null ? bearing : null)
		.zoom(zoom != null ? zoom : null);

		CameraPosition location = builder.build();

		CameraUpdate position = CameraUpdateFactory.newCameraPosition(location);
		mapa.animateCamera(position);
	}


	public void setVisualizacao(int tipoVisualizacao){
		mapa.setMapType(tipoVisualizacao);
	}

	public void adicionaLinhaMapa(List<LatLng> listaCoordenadas, int cor){

		rectOptions = new PolylineOptions();


		if(listaCoordenadas != null && listaCoordenadas.size() > 0){
			Iterator<LatLng> it = listaCoordenadas.iterator();
			while (it.hasNext()) {
				LatLng latLng = (LatLng) it.next();
				rectOptions.add(latLng);
			}
		}

		rectOptions.color(cor);

		if(rectOptions != null && rectOptions.getPoints().size() > 0){
			mHandlerAddLinha.sendEmptyMessage(0);
		}

	}


	Handler mHandlerAddLinha = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Polyline polyline = mapa.addPolyline(rectOptions);
			polyline.setGeodesic(true);
		};
	};


	Handler mHandlerAddMarcador = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			mapa.addMarker(marker);

			/*// Customiza a janela ao clicar em um marcador
			mapa.setInfoWindowAdapter(new InfoWindowAdapter() {
				@Override
				public View getInfoWindow(Marker marker) {
					FrameLayout frame = new FrameLayout(contexto);
					frame.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					frame.setBackgroundResource(R.drawable.janela_marker);
					// Janela da borda
					return frame;
				}

				@Override
				public View getInfoContents(Marker marker) {
					// View com o conteúdo
					LinearLayout linear = new LinearLayout(contexto);
					linear.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					linear.setOrientation(LinearLayout.VERTICAL);

					TextView tTitle = new TextView(contexto);
					tTitle.setText(marker.getTitle());
					tTitle.setTextColor(Color.RED);
					linear.addView(tTitle);

					TextView tSnippet = new TextView(contexto);
					tSnippet.setText(marker.getSnippet());
					tSnippet.setTextColor(Color.BLUE);
					linear.addView(tSnippet);

					return linear;
				}
			});*/
		};
	};


	/*public void marcadores(LatLng latLng, String titulo, String mensagem) {

		//Adiciona um marcador
		marker = new MarkerOptions().position(latLng).title(titulo).snippet(mensagem);
		marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.seta_mapa));

		mHandlerAddMarcador.sendEmptyMessage(0);
	}*/


}
