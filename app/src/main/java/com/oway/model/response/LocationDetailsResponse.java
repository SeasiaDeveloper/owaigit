package com.oway.model.response;

import java.util.List;


//@Data
public class LocationDetailsResponse {


    /**
     * formatted_address : Unnamed Road, Phase 8, Industrial Area, Sector 73, Sahibzada Ajit Singh Nagar, Punjab 140308, India
     * geometry : {"bounds":{"northeast":{"lat":30.7106705,"lng":76.70986789999999},"southwest":{"lat":30.7097869,"lng":76.7084782}},"location":{"lat":30.7102287,"lng":76.70917299999999},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":30.71157768029149,"lng":76.7105220302915},"southwest":{"lat":30.7088797197085,"lng":76.7078240697085}}}
     * place_id : ChIJJ8OaIvXuDzkRSO9FELNtWSM
     * types : ["route"]
     */

    private String formatted_address;
    private GeometryBean geometry;
    private String place_id;
    private List<String> types;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public GeometryBean getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryBean geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public static class GeometryBean {
        /**
         * bounds : {"northeast":{"lat":30.7106705,"lng":76.70986789999999},"southwest":{"lat":30.7097869,"lng":76.7084782}}
         * location : {"lat":30.7102287,"lng":76.70917299999999}
         * location_type : GEOMETRIC_CENTER
         * viewport : {"northeast":{"lat":30.71157768029149,"lng":76.7105220302915},"southwest":{"lat":30.7088797197085,"lng":76.7078240697085}}
         */

        private BoundsBean bounds;
        private LocationBean location;
        private String location_type;
        private ViewportBean viewport;

        public BoundsBean getBounds() {
            return bounds;
        }

        public void setBounds(BoundsBean bounds) {
            this.bounds = bounds;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getLocation_type() {
            return location_type;
        }

        public void setLocation_type(String location_type) {
            this.location_type = location_type;
        }

        public ViewportBean getViewport() {
            return viewport;
        }

        public void setViewport(ViewportBean viewport) {
            this.viewport = viewport;
        }

        public static class BoundsBean {
            /**
             * northeast : {"lat":30.7106705,"lng":76.70986789999999}
             * southwest : {"lat":30.7097869,"lng":76.7084782}
             */

            private NortheastBean northeast;
            private SouthwestBean southwest;

            public NortheastBean getNortheast() {
                return northeast;
            }

            public void setNortheast(NortheastBean northeast) {
                this.northeast = northeast;
            }

            public SouthwestBean getSouthwest() {
                return southwest;
            }

            public void setSouthwest(SouthwestBean southwest) {
                this.southwest = southwest;
            }

            public static class NortheastBean {
                /**
                 * lat : 30.7106705
                 * lng : 76.70986789999999
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class SouthwestBean {
                /**
                 * lat : 30.7097869
                 * lng : 76.7084782
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }

        public static class LocationBean {
            /**
             * lat : 30.7102287
             * lng : 76.70917299999999
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }

        public static class ViewportBean {
            /**
             * northeast : {"lat":30.71157768029149,"lng":76.7105220302915}
             * southwest : {"lat":30.7088797197085,"lng":76.7078240697085}
             */

            private NortheastBeanX northeast;
            private SouthwestBeanX southwest;

            public NortheastBeanX getNortheast() {
                return northeast;
            }

            public void setNortheast(NortheastBeanX northeast) {
                this.northeast = northeast;
            }

            public SouthwestBeanX getSouthwest() {
                return southwest;
            }

            public void setSouthwest(SouthwestBeanX southwest) {
                this.southwest = southwest;
            }

            public static class NortheastBeanX {
                /**
                 * lat : 30.71157768029149
                 * lng : 76.7105220302915
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class SouthwestBeanX {
                /**
                 * lat : 30.7088797197085
                 * lng : 76.7078240697085
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }
    }
}
