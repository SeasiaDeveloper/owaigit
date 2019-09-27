package com.oway.model.response;

import java.util.List;

public class GetRecommendedPlacesResponse {

    /**
     * results : [{"_id":"5d8b35c1c68fe91e2201ee7e","formatted_address":"","geometry":{"location":{"lat":30.730095,"lng":76.71314699999999},"viewport":{"northeast":{"lat":30.7314436802915,"lng":76.7144698302915},"southwest":{"lat":30.72874571970851,"lng":76.7117718697085}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/lodging-71.png","id":"c71e4fdb04a37c32533e1954f742d22b44d6c7c3","name":"OYO 1731 Mohali","photos":[{"height":428,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109285599428450968038/photos\">OYO 1731 Mohali<\/a>"],"photo_reference":"CmRaAAAASu2-1E8BUF1HUIY29gE5MvwdzQKGVbfqXdjUakqk267PoUSsZK0Y5l8O0Pt7ZBQwPCjCBvA1NhEFh4cJUnqi4IgqUfodJtJB7gjffqKaS7zdmiVp_NDU_-KAsJt4squ0EhD2SbYrQ8ZyHUbqNstavoJGGhT3o7KY5MWaa7Z86WDAChBtIeKm0w","width":761}],"place_id":"ChIJORvpK2ruDzkRvQ1QC_NQ8_A","reference":"ChIJORvpK2ruDzkRvQ1QC_NQ8_A","types":["lodging","point_of_interest","establishment"],"loc":{"type":"Point","coordinates":[76.71314699999999,30.730095]},"updated_at":"2019-09-25 16:39:13","created_at":"2019-09-25 16:39:13","distance":{"value":22}},{"_id":"5d8b35c1c68fe91e2201ee7b","formatted_address":"","geometry":{"location":{"lat":30.692267,"lng":76.7341409},"viewport":{"northeast":{"lat":30.6936420302915,"lng":76.73552873029149},"southwest":{"lat":30.6909440697085,"lng":76.73283076970849}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/lodging-71.png","id":"aa06262583a4d77c7e390892eb6950f0071df2d2","name":"OYO Rooms Fortis Hospital Mohali","photos":null,"place_id":"ChIJkZMa-R_sDzkRlT9XQWgtMjE","reference":"ChIJkZMa-R_sDzkRlT9XQWgtMjE","types":["lodging","point_of_interest","establishment"],"loc":{"type":"Point","coordinates":[76.7341409,30.692267]},"updated_at":"2019-09-25 16:39:13","created_at":"2019-09-25 16:39:13","distance":{"value":22}},{"_id":"5d8cdbd363ff8c4f230d536e","formatted_address":"","geometry":{"location":{"lat":30.680869,"lng":76.60537699999999},"viewport":{"northeast":{"lat":30.68224882989272,"lng":76.60673807989274},"southwest":{"lat":30.67954917010728,"lng":76.60403842010729}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/school-71.png","id":"509cd20f64488404ed8a735ee76a8ac1fc548bc9","name":"Chandigarh Group of Colleges Jhanjeri Mohali","photos":[{"height":2250,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/110380131000640277668/photos\">A Google User<\/a>"],"photo_reference":"CmRaAAAA86nH1W6TVq5_FvsBpsKuhFCGtsjlmk9474bAcWG5h1oB0mGCz-7pgnrK0dVLhy9lsIrgcV8kYLCe6yVsNn2ZJwV6_7PwjNobcLLWvN9xtnxL8GDfu3S9dieOcV-C3t5xEhDMH36EAnbbh3R_jnukJLVXGhT31nqeA_eP4JPwUQH6vYm2plLzyQ","width":4000}],"place_id":"ChIJ_VxzlbflDzkR-yBnC0O0h7I","reference":"ChIJ_VxzlbflDzkR-yBnC0O0h7I","types":["university","point_of_interest","establishment"],"loc":{"type":"Point","coordinates":[76.60537699999999,30.680869]},"updated_at":"2019-09-26 22:40:03","created_at":"2019-09-26 22:40:03","distance":{"value":22}}]
     * Code : 200
     * html_attributions : []
     */

    private int Code;
    private List<ResultsBean> results;
    private List<?> html_attributions;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public List<?> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<?> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public static class ResultsBean {
        /**
         * _id : 5d8b35c1c68fe91e2201ee7e
         * formatted_address :
         * geometry : {"location":{"lat":30.730095,"lng":76.71314699999999},"viewport":{"northeast":{"lat":30.7314436802915,"lng":76.7144698302915},"southwest":{"lat":30.72874571970851,"lng":76.7117718697085}}}
         * icon : https://maps.gstatic.com/mapfiles/place_api/icons/lodging-71.png
         * id : c71e4fdb04a37c32533e1954f742d22b44d6c7c3
         * name : OYO 1731 Mohali
         * photos : [{"height":428,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109285599428450968038/photos\">OYO 1731 Mohali<\/a>"],"photo_reference":"CmRaAAAASu2-1E8BUF1HUIY29gE5MvwdzQKGVbfqXdjUakqk267PoUSsZK0Y5l8O0Pt7ZBQwPCjCBvA1NhEFh4cJUnqi4IgqUfodJtJB7gjffqKaS7zdmiVp_NDU_-KAsJt4squ0EhD2SbYrQ8ZyHUbqNstavoJGGhT3o7KY5MWaa7Z86WDAChBtIeKm0w","width":761}]
         * place_id : ChIJORvpK2ruDzkRvQ1QC_NQ8_A
         * reference : ChIJORvpK2ruDzkRvQ1QC_NQ8_A
         * types : ["lodging","point_of_interest","establishment"]
         * loc : {"type":"Point","coordinates":[76.71314699999999,30.730095]}
         * updated_at : 2019-09-25 16:39:13
         * created_at : 2019-09-25 16:39:13
         * distance : {"value":22}
         */

        private String _id;
        private String formatted_address;
        private GeometryBean geometry;
        private String icon;
        private String id;
        private String name;
        private String place_id;
        private String reference;
        private LocBean loc;
        private String updated_at;
        private String created_at;
        private DistanceBean distance;
        private List<PhotosBean> photos;
        private List<String> types;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public LocBean getLoc() {
            return loc;
        }

        public void setLoc(LocBean loc) {
            this.loc = loc;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public DistanceBean getDistance() {
            return distance;
        }

        public void setDistance(DistanceBean distance) {
            this.distance = distance;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * location : {"lat":30.730095,"lng":76.71314699999999}
             * viewport : {"northeast":{"lat":30.7314436802915,"lng":76.7144698302915},"southwest":{"lat":30.72874571970851,"lng":76.7117718697085}}
             */

            private LocationBean location;
            private ViewportBean viewport;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class LocationBean {
                /**
                 * lat : 30.730095
                 * lng : 76.71314699999999
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
                 * northeast : {"lat":30.7314436802915,"lng":76.7144698302915}
                 * southwest : {"lat":30.72874571970851,"lng":76.7117718697085}
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
                     * lat : 30.7314436802915
                     * lng : 76.7144698302915
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
                     * lat : 30.72874571970851
                     * lng : 76.7117718697085
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

        public static class LocBean {
            /**
             * type : Point
             * coordinates : [76.71314699999999,30.730095]
             */

            private String type;
            private List<Double> coordinates;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Double> getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(List<Double> coordinates) {
                this.coordinates = coordinates;
            }
        }

        public static class DistanceBean {
            /**
             * value : 22
             */

            private double value;

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

        public static class PhotosBean {
            /**
             * height : 428
             * html_attributions : ["<a href=\"https://maps.google.com/maps/contrib/109285599428450968038/photos\">OYO 1731 Mohali<\/a>"]
             * photo_reference : CmRaAAAASu2-1E8BUF1HUIY29gE5MvwdzQKGVbfqXdjUakqk267PoUSsZK0Y5l8O0Pt7ZBQwPCjCBvA1NhEFh4cJUnqi4IgqUfodJtJB7gjffqKaS7zdmiVp_NDU_-KAsJt4squ0EhD2SbYrQ8ZyHUbqNstavoJGGhT3o7KY5MWaa7Z86WDAChBtIeKm0w
             * width : 761
             */

            private int height;
            private String photo_reference;
            private int width;
            private List<String> html_attributions;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getHtml_attributions() {
                return html_attributions;
            }

            public void setHtml_attributions(List<String> html_attributions) {
                this.html_attributions = html_attributions;
            }
        }
    }
}